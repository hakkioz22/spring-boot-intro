package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentBookDTO;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request){
        return "Welcome to Student Controller" + request.getServletPath();
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody StudentDTO studentDTO){
        studentService.saveStudent(studentDTO);

        Map<String,String> map = new HashMap<>();
        map.put("message","Student created succesfully");
        map.put("success","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
       List<Student> allStudent = studentService.getAllStudent();
       return ResponseEntity.ok(allStudent);
    }

    // /query?id=1
    @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam("id")Long id){
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentByPath(@PathVariable("id")Long id){
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO){

       studentService.updateStudent(id,studentDTO);

       Map<String,String> map = new HashMap<>();
       map.put("message","Student updated succesfully");
       map.put("success","true");

       return ResponseEntity.ok(map);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

        Map<String,String> map = new HashMap<>();
        map.put("message","Student deleted succesfully");
        map.put("success","true");

        return ResponseEntity.ok(map);
    }
    @GetMapping("/email")
    public ResponseEntity<Map<String,String>> checkEmail(@RequestParam("email") String email){
        Boolean isExist = studentService.existsByEmail(email);

        Map<String,String> map = new HashMap<>();
        map.put("isEmailExist",isExist.toString());

        return ResponseEntity.ok(map);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<Student>> getStudentPage(@RequestParam("page") int page,@RequestParam("size") int size,
                                                        @RequestParam("sort") String prop,@RequestParam(value = "direction",required = false,defaultValue = "DESC") Direction direction){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Student> studentPage = studentService.getStudentPage(pageable);

        return ResponseEntity.ok(studentPage);
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentsEqualGrade(@PathVariable("grade") Integer grade){
        List<Student> students = studentService.findAllEqualsGrade(grade);

        return ResponseEntity.ok(students);
    }

    @GetMapping("/query/dto")
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id")Long id){
        StudentDTO student = studentService.findStudentDTOById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/query/list")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> allStudent = studentService.getStudents();
        return ResponseEntity.ok(allStudent);
    }

    @GetMapping("/query/list/dto")
    public ResponseEntity<List<StudentDTO>> getStudentsDTO(){
        List<StudentDTO> allStudentDTO = studentService.getStudentsDTO();
        return ResponseEntity.ok(allStudentDTO);
    }

    //http://localhost:8080/student/query/studentbook
    @GetMapping("/query/studentbook")
    public ResponseEntity<List<StudentBookDTO>> getStudentBookDTOs() {
        List<StudentBookDTO> allStudentBookDTO = studentService.getStudentBookDTO();
        return ResponseEntity.ok(allStudentBookDTO);
    }


    @GetMapping("/query/studentbook/pages")
    public ResponseEntity<Page<StudentBookDTO>> getStudentBookDTOPage(@RequestParam("page") int page,@RequestParam("size") int size,
                                                                      @RequestParam("sort") String prop,
                                                                      @RequestParam(value = "direction",required = false,defaultValue = "DESC") Direction direction){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<StudentBookDTO> studentBookDTOPage = studentService.getBookStudentDTOPage(pageable);
        return ResponseEntity.ok(studentBookDTOPage);
    }





}
