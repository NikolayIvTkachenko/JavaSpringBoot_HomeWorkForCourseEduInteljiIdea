package com.rsh.tkachenko.microservice.AppToDo.utilities;

public class UserNotInDatabaseException extends Exception {

    public UserNotInDatabaseException(String message){
        super(message);
    }
}