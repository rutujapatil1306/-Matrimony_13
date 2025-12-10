package com.spring.jwt.exception;

import org.springframework.http.HttpStatus;

public class ContactNotFoundException extends RuntimeException{

    private final HttpStatus status;

    public ContactNotFoundException(String message){

        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
