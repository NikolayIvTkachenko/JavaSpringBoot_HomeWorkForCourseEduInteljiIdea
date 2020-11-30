package com.rsh.tkachenko.microservice.AppToDo.services;

import com.rsh.tkachenko.microservice.AppToDo.entities.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> getToDos(String email);

    ToDo addToDo(ToDo toDo);

    void deleteToDo(Integer id);
}
