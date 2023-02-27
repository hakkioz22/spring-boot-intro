package com.tpe;

import com.tpe.controller.StudentController;
import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClRunner implements CommandLineRunner {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentController controller;

    @Override // Once application run completely, this method will be called- program içi uygulamaya erişim
    public void run(String... args) throws Exception {
//        List<Student> list = studentService.getAllStudent();
//        list.forEach(s-> System.out.println(s.getFirstName()+s.getLastName()));

//        ResponseEntity<Student> student = controller.getStudent(4L);
//        System.out.println(student.getBody().getFirstName());
//        System.out.println(student.getStatusCode());
    }
}
