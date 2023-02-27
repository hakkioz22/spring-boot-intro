package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    //custom query with @Query



    Boolean existsByEmail(String email);


    //Derived queries on JPA
    List<Student> findByFirstName(String name);
    List<Student> queryByFirstName(String name); //same with above method

    List<Student> getByFirstName(String name);

    List<Student> findByFirstNameLike(String name);

    List<Student> findByFirstNameOrderByFirstName(String name);

    List<Student> findByFirstNameOrderByFirstNameAsc(String name);

    List<Student> findByFirstNameStartingWith(String name);
}
