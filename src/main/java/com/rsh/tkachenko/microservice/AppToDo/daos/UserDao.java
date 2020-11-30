package com.rsh.tkachenko.microservice.AppToDo.daos;

import com.rsh.tkachenko.microservice.AppToDo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserDao  extends JpaRepository<User, String> {

    //name strategy
    Optional<User> findUserByEmail(String email);

    //@Quary annotation
    //@Query (value="SELECT * FROM users WHERE email =:email", nativeQuery = true)
    //Optional<User> findUserByTheEmail(@Param("email") String email);

    //native method
    //User findOne(String email);


}
