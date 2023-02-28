package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentBookDTO;
import com.tpe.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StudentService {

    List<Student> getAllStudent();
    Student findStudentById(Long id);
    void saveStudent(StudentDTO studentDTO);
    void updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
    boolean existsByEmail(String email);
    Page<Student> getStudentPage(Pageable pageable);

    List<Student> findAllEqualsGrade(Integer grade);

    StudentDTO findStudentDTOById(Long id);

    List<Student> getStudents();
    List<StudentDTO> getStudentsDTO();

    List<StudentBookDTO> getStudentBookDTO();

    Page<StudentBookDTO> getBookStudentDTOPage(Pageable pageable);


}
