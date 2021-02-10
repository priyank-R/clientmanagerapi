package com.springproject.clientmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionInterceptor {
    @ExceptionHandler(IncompleteDataException.class)
    public ResponseEntity<?> handleIncompleteDataException(IncompleteDataException e, WebRequest request){
            ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
            return new ResponseEntity(errorDetails,e.getHttpStatus());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity handleEmailAlreadyExistsException(EmailAlreadyExistsException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,e.getHttpStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails,e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralExceptions(Exception e, WebRequest request){
        System.out.println("gets in here !");
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
