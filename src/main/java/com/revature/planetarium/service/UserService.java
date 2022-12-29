package com.revature.planetarium.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.planetarium.Entities.User;
import com.revature.planetarium.exception.EntityNotFound;
import com.revature.planetarium.repository.UserDao;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    public String createUser(User user){
        this.userDao.createUser(user.getUsername(), user.getPassword());
        return "Create successfully";
    }

    public User findByUserName(String username){
        Optional<User> possibleUser = this.userDao.findByUsername(username);
        if(possibleUser.isPresent()){
            return possibleUser.get();
        } else {
            throw new EntityNotFound("Username not found");
        }
    }
}
