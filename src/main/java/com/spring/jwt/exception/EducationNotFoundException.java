package com.spring.jwt.exception;

public class EducationNotFoundException extends RuntimeException {
    public EducationNotFoundException(String message) {
        super(message);
    }
}
