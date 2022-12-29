package com.revature.planetarium.exception;

public class AuthenticationFailed extends RuntimeException {
    public AuthenticationFailed(String message){
        super(message);
    }
}
