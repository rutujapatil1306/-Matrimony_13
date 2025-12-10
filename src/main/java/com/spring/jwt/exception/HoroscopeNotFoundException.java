package com.spring.jwt.exception;

public class HoroscopeNotFoundException extends RuntimeException {
    public HoroscopeNotFoundException(String message) {
        super(message);
    }
}
