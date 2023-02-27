package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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





}
