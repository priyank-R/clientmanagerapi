package com.springproject.clientmanager.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;

@ResponseStatus(value= HttpStatus.BAD_REQUEST,reason = "Incomplete Data !")
public class IncompleteDataException extends RuntimeException{
    private String message =
            "Incomplete data provided in request -  Fields Required: ";
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;


    public IncompleteDataException(String[] fields) {
        this.message = this.message + Arrays.toString(fields);
    }

    @Override
    public String getMessage() {
        return this.message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
