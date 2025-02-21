package com.salesianos.geekhub.dto;

public record LoginRequest(
        String username, String password
) {
}