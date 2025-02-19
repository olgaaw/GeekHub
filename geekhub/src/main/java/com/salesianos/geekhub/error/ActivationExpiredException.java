package com.salesianos.geekhub.error;

public class ActivationExpiredException extends RuntimeException {
    public ActivationExpiredException(String s) {
        super(s);
    }
}