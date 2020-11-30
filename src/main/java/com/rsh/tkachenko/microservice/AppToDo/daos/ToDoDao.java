package com.rsh.tkachenko.microservice.AppToDo.daos;

import com.rsh.tkachenko.microservice.AppToDo.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoDao extends JpaRepository<ToDo, Integer> {

    //name strategy
    List<ToDo> findByFkUser(String email);


}
