package com.revature.planetarium.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.revature.planetarium.Entities.Moon;



public interface MoonDao extends JpaRepository<Moon, Integer> {
    
    Optional<Moon> findByMoonName(String name);

    @Transactional
    @Modifying
    @Query(value = ("insert into moons values (default, :moonName, :planetId)"), nativeQuery = true)
    void createMoon(@Param("moonName") String moonName, @Param("planetId") int planetId);


    @Transactional
    @Query(value = ("select * from moons where myplanetid = :planetId"), nativeQuery = true)
    List<Moon> getMoonFromPlanet(@Param("planetId") int planetId);
}
