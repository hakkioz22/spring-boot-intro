package com.tpe.repository;

import com.tpe.domain.MyUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {

    @EntityGraph(attributePaths = "roles")//on ManyToMany relation default fetch type is lazy, we must change to EAGER
    Optional<MyUser> findByUserName(String userName);//derived method

}
