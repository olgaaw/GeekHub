package com.salesianos.geekhub.security.jwt.exceptionhandling;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}