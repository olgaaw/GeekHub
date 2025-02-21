package com.salesianos.geekhub.security.jwt.refresh;

public record RefreshTokenRequest(
        String refreshToken
) {
}