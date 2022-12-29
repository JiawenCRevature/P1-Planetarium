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


import com.revature.planetarium.Entities.Planet;
import com.revature.planetarium.exception.AuthenticationFailed;
import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.service.PlanetService;

@RestController
public class PlanetController {
    private static Logger planetLogger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> psqlException(PSQLException e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFail(AuthenticationFailed e){
        planetLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @Autowired
    private PlanetService planetService;

    @GetMapping("/api/planet")
    public ResponseEntity<List<Planet>> getAllPlanet(){
        return new ResponseEntity<>(this.planetService.findAllPlanets(), HttpStatus.OK);
    }
    
    @GetMapping("/api/planet/{name}")
    public ResponseEntity<Planet> getPlanetByName(@PathVariable String name){
        return new ResponseEntity<>(this.planetService.findPlanetByName(name), HttpStatus.OK);
    }

    @GetMapping("/api/planet/id/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.findPlanetById(id), HttpStatus.OK);
    }

    @PostMapping("/api/planet")
    public ResponseEntity<String> createPlanet(@RequestBody Planet planet){
        return new ResponseEntity<>(this.planetService.createPlanet(planet), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/planet/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.deletePlanetById(id), HttpStatus.OK);
    }
}
