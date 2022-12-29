package com.revature.planetarium.controller;


import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.planetarium.Entities.User;
import com.revature.planetarium.exception.AuthenticationFailed;
import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.service.UserService;

@RestController
public class UserController {
    private static Logger userLogger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        userLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> psqlException(PSQLException e){
        userLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFail(AuthenticationFailed e){
        userLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }
    
    @GetMapping("/api/user/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(this.userService.findByUserName(name), HttpStatus.OK);
    }
}
