package com.rsh.tkachenko.microservice.AppToDo.controllers;

//import io.jsonwebtoken.ExpiredJwtException;
import com.rsh.tkachenko.microservice.AppToDo.entities.ToDo;
import com.rsh.tkachenko.microservice.AppToDo.entities.User;
import com.rsh.tkachenko.microservice.AppToDo.services.LoginService;
import com.rsh.tkachenko.microservice.AppToDo.services.ToDoService;
import com.rsh.tkachenko.microservice.AppToDo.utilities.JsonResponseBody;
import com.rsh.tkachenko.microservice.AppToDo.utilities.ToDoValidator;
import com.rsh.tkachenko.microservice.AppToDo.utilities.UserNotInDatabaseException;
import com.rsh.tkachenko.microservice.AppToDo.utilities.UserNotLoggedException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    LoginService loginService;

    @Autowired
    ToDoService toDoService;

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @RequestMapping("/userInOutput")
    public User giveMeAUser(){
        return new User("nick@gmail.com", "Nick", "PasswodEncrypted");
    }

    @RequestMapping("/todoInInput1")
    public String toDoInInput1(ToDo toDo){
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput2")
    public String toDoInInput2(@Valid ToDo toDo){
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput3")
    public String toDoInInput3(@Valid ToDo toDo, BindingResult result){
        if(result.hasErrors()){
            return "I return the error in the format I like: " + result.toString();
        }
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput4")
    public String toDoInInput4(ToDo toDo, BindingResult result){
        ToDoValidator validator = new ToDoValidator();
        validator.validate(toDo, result);
        if(result.hasErrors()){
            return "I return the error in the format I like: " + result.toString();
        }
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput5")
    public String toDoInInput5(@Valid ToDo toDo, BindingResult result){
        ToDoValidator validator = new ToDoValidator();
        validator.validate(toDo, result);
        if(result.hasErrors()){
            return "I return the error in the format I like: " + result.toString();
        }
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping(value = "/login", method= POST)
    public ResponseEntity<JsonResponseBody> login(@RequestParam(value="email") String email, @RequestParam(value="password") String pwd){

        try {
            Optional<User> userr = loginService.getUserFromDb(email, pwd);
            User user = userr.get();
            String jwt = loginService.createJwt(email, user.getName(), new Date());
            return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Success! User logged in!"));
        }catch(UserNotInDatabaseException e1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e1.toString()));
        }catch(UnsupportedEncodingException e2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e2.toString()));
        }
    }

    @RequestMapping("/showToDos")
    public ResponseEntity<JsonResponseBody> showToDos(HttpServletRequest request){

        try {
            Map<String, Object> userData = loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), toDoService.getToDos((String) userData.get("email"))));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e1.toString()));
        }catch(UserNotLoggedException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired: " + e3.toString()));
        }
    }

    @RequestMapping(value="/newToDo", method=POST)
    public ResponseEntity<JsonResponseBody> newToDo(HttpServletRequest request, @Valid ToDo toDo, BindingResult result){

        ToDoValidator validator = new ToDoValidator();
        validator.validate(toDo, result);

        if(result.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Data not valid: " + result.toString()));
        }

        try {
            loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), toDoService.addToDo(toDo)));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e1.toString()));
        }catch(UserNotLoggedException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired: " + e3.toString()));
        }
    }

    @RequestMapping("/deleteToDo/{id}")
    public ResponseEntity<JsonResponseBody> deleteToDo(HttpServletRequest request, @PathVariable(name="id") Integer toDoId){

        try{
            loginService.verifyJwtAndGetData(request);
            toDoService.deleteToDo(toDoId);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "ToDo correctly delete"));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e1.toString()));
        }catch(UserNotLoggedException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired: " + e3.toString()));
        }
    }
}
