package com.rsh.tkachenko.microservice.AppToDo.services;

import com.rsh.tkachenko.microservice.AppToDo.daos.ToDoDao;
import com.rsh.tkachenko.microservice.AppToDo.entities.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoDao toDoDao;

    @Override
    public List<ToDo> getToDos(String email){
        return toDoDao.findByFkUser(email);
    }

    @Override
    public ToDo addToDo(ToDo toDo){
        return toDoDao.save(toDo);
    }

    @Override
    public void deleteToDo(Integer id){
        //toDoDao.delete(id);
    }
}
