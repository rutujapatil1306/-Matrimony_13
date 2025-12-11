package com.spring.jwt.exception;

public class MissingDocumentNameException extends RuntimeException {
    public MissingDocumentNameException(String message) {
        super(message);
    }
}
