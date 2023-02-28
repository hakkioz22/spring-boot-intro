package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    //custom query with @Query
    @Query("SELECT s FROM Student s WHERE s.grade=:pGrade")
    List<Student> findAllEqualsGrade(@Param("pGrade")Integer grade);

    @Query(value = "SELECT * FROM Student s WHERE s.grade=:pGrade",nativeQuery = true)
    List<Student> findAllEqualsGradeWithSQL(@Param("pGrade")Integer grade);

    @Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s  WHERE s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);

    @Query("FROM Student")
    List<Student> findAllStudent();

    @EntityGraph(attributePaths = {"books"}) //fetchtype ı EAGER'a dönüştürdük default LAZY for manytoone
    List<Student> findAll();


    //Derived queries on JPA
    Boolean existsByEmail(String email);
    List<Student> findByFirstName(String name);
    List<Student> queryByFirstName(String name); //same with above method

    List<Student> getByFirstName(String name);

    List<Student> findByFirstNameLike(String name);

    List<Student> findByFirstNameOrderByFirstName(String name);

    List<Student> findByFirstNameOrderByFirstNameAsc(String name);

    List<Student> findByFirstNameStartingWith(String name);
}
