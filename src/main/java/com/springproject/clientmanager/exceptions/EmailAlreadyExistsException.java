package com.springproject.clientmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Email already exists !")
public class EmailAlreadyExistsException extends RuntimeException{
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public EmailAlreadyExistsException() {
        super("The given email already exists!");
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
