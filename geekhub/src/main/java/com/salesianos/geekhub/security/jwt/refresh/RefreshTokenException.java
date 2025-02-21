package com.salesianos.geekhub.security.jwt.refresh;

import com.salesianos.geekhub.security.jwt.exceptionhandling.JwtException;

public class RefreshTokenException extends JwtException {
    public RefreshTokenException(String s) {
        super(s);
    }
}