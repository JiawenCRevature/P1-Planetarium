package com.revature.planetarium.controller;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.planetarium.Entities.User;

import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.service.UserService;



@RestController
public class AuthenticationController {
    private static Logger authLogger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> authenticationFailed(EntityNotFound e){
        authLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpSession httpSession, @RequestBody User user){
        User u = userService.findByUserName(user.getUsername());
        if(u != null && u.getPassword().equals(user.getPassword())){
            httpSession.setAttribute("user", user);
            return new ResponseEntity<>("Login successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Password not correct", HttpStatus.UNAUTHORIZED);
        }
        

        
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "Logout successfully";
    }

    @GetMapping("/break")
    public ResponseEntity<String> breakServer(){
        authLogger.error("Intentionally break the server");
        return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

