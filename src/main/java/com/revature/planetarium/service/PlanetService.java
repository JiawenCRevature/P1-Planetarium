package com.revature.planetarium.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.planetarium.Entities.Planet;
import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.repository.PlanetDao;

@Service
public class PlanetService {
    
    @Autowired
    private PlanetDao planetDao;

    public List<Planet> findAllPlanets(){
       List<Planet> planets = this.planetDao.findAll();
       if(planets.size() != 0){
            return planets;
       } else {
            throw new EntityNotFound("Planet not exists");
       }
    }

    public Planet findPlanetByName(String name){
        Optional<Planet> possiblePlanet = this.planetDao.findByPlanetName(name);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("Planet not found");
        }
    }

    public Planet findPlanetById(int id){
        Optional<Planet> possiblePlanet = this.planetDao.findById(id);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("Planet with given id not found");
        }
    }

    public String createPlanet(Planet planet){
        this.planetDao.createPlanet(planet.getPlanetName(), planet.getOwnerid());
        return "Planet creates successfully";
    }

    public String deletePlanetById(int id){
        this.planetDao.deleteById(id);
        return "Planet with given id: " + id + " has been deleted successfully";
    }
}
