package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id "+id));
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {
        boolean isExist = existsByEmail(studentDTO.getEmail());

        if(isExist){
            throw new ConflictException("Email already exist! -> " + studentDTO.getEmail());
        }

        //Conversion DTO to student
        Student student = new Student();

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setEmail(studentDTO.getEmail());

        studentRepository.save(student);
    }


    @Override
    public void updateStudent(Long id, StudentDTO studentDTO) {

        Student foundStudent = findStudentById(id);

        boolean isExist = existsByEmail(studentDTO.getEmail());

        if(isExist && !studentDTO.getEmail().equals(foundStudent.getEmail())){
            throw new ConflictException("Email already exist! -> " + studentDTO.getEmail());
        }

        foundStudent.setFirstName(studentDTO.getFirstName());
        foundStudent.setLastName(studentDTO.getLastName());
        foundStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        foundStudent.setGrade(studentDTO.getGrade());

        studentRepository.save(foundStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student foundStudent = findStudentById(id);
        studentRepository.delete(foundStudent);
    }

    @Override
    public boolean existsByEmail(String email) {
       return studentRepository.existsByEmail(email);
    }

    @Override
    public Page<Student> getStudentPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
