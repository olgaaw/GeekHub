package com.salesianos.geekhub.dto;

import java.util.Date;

public record CreateUserRequest(
        String username,
        String email,
        String password,
        String verifyPassword,
        String name,
        String surname,
        String phone,
        String adress,
        int cp,
        String gender,
        Date birthday,
        String profilePicture
) {
}
