package com.salesianos.geekhub.dto.user;

public record LoginRequest(
        String username, String password
) {
}