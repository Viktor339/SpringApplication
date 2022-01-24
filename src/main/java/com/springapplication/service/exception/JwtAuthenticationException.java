package com.springapplication.service.exception;

public class JwtAuthenticationException extends RuntimeException{
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
