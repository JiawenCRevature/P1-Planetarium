package com.revature.planetarium.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.planetarium.Entities.Moon;
import com.revature.planetarium.exception.AuthenticationFailed;
import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.service.MoonService;

@RestController
public class MoonController {
    private static Logger moonLogger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> psqlException(PSQLException e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFailed(AuthenticationFailed e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    
    @Autowired
    private MoonService moonService;

    @GetMapping("/api/moon")
    public ResponseEntity<List<Moon>> getAllMoons(){
        return new ResponseEntity<>(this.moonService.findAllMoons(), HttpStatus.OK);
    }
    
    @GetMapping("/api/moon/{name}")
    public ResponseEntity<Moon> getMoonByName(@PathVariable String name){
        return new ResponseEntity<>(this.moonService.getMoonByName(name), HttpStatus.OK);
    }

    @GetMapping("/api/moon/id/{id}")
    public ResponseEntity<Moon> getMoonById(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.getMoonById(id), HttpStatus.OK);
    }

    @GetMapping("/api/planet/{id}/moons")
    public ResponseEntity<List<Moon>> getMoonByPlanetId(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.getMoonFromPlanet(id), HttpStatus.OK);
    }

    @PostMapping("/api/moon")
    public ResponseEntity<String> createMoon(@RequestBody Moon moon){
        return new ResponseEntity<>(this.moonService.createMoon(moon), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/moon/{id}")
    public ResponseEntity<String> deleteMoon(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.deleteMoonById(id), HttpStatus.OK);
    }


}
