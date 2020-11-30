package com.rsh.tkachenko.microservice.AppToDo.services;

import com.rsh.tkachenko.microservice.AppToDo.entities.User;
import com.rsh.tkachenko.microservice.AppToDo.utilities.UserNotInDatabaseException;
import com.rsh.tkachenko.microservice.AppToDo.utilities.UserNotLoggedException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface LoginService {

    Optional<User> getUserFromDb(String email, String pwd) throws UserNotInDatabaseException;

    String createJwt(String email, String name, Date date) throws UnsupportedEncodingException;

    Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws  UnsupportedEncodingException, UserNotLoggedException;

}
