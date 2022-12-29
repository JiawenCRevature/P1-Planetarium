package com.revature.planetarium.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.revature.planetarium.Entities.Planet;

public interface PlanetDao extends JpaRepository<Planet, Integer>{
    
    Optional<Planet> findByPlanetName(String planetName);

    @Transactional
    @Modifying
    @Query(value = ("insert into planets values(default, :planetName, :ownerid)"), nativeQuery = true)
    void createPlanet(@Param("planetName") String planetName, @Param("ownerid") int ownerid);
}
