package com.revature.planetarium.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.planetarium.Entities.Moon;
import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.repository.MoonDao;

@Service
public class MoonService {
    
    @Autowired
    private MoonDao moonDao;

    public List<Moon> findAllMoons(){
        List<Moon> moons = this.moonDao.findAll();
        if(moons.size() != 0){
            return moons;
        } else {
            throw new EntityNotFound("No Moon exists");
        } 
    }

    public Moon getMoonByName(String name){
        Optional<Moon> possibleMoon = this.moonDao.findByMoonName(name);
        if(possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new EntityNotFound("Moon not found");
        }
    }

    public Moon getMoonById(int id){
        Optional<Moon> possibleMoon = this.moonDao.findById(id);
        if(possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new EntityNotFound("Moon with given id not found");
        }
    }

    public List<Moon> getMoonFromPlanet(int planetid){
        List<Moon> moons = this.moonDao.getMoonFromPlanet(planetid);
        if(moons.size() != 0){
            return moons;
        } else {
            throw new EntityNotFound("Moon with given planet id not found");
        }
    }

    public String createMoon(Moon moon){
        this.moonDao.createMoon(moon.getMoonName(), moon.getMyplanetid());
        return "Moon creates successfully";
    }

    public String deleteMoonById(int id){
        this.moonDao.deleteById(id);
        return "Moon with given id " + id + " deleted successfully";
    }
}
