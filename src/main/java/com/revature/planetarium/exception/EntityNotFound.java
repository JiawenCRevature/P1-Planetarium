package com.revature.planetarium.exception;

public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String message){
        super(message);
    }
}
