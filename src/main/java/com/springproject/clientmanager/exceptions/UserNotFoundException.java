package com.springproject.clientmanager.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public UserNotFoundException(String userEmail) {
        super("The given user "+userEmail+" is not found in the records");
    }

    public UserNotFoundException(String message,String comments){
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
