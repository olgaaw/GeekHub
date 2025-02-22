package com.salesianos.geekhub.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianos.geekhub.validation.FieldsValueMatch;
import com.salesianos.geekhub.validation.UniqueUsername;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "verifyPassword",
                message = "Los valores de password y verifyPassword no coinciden"),
})
public record CreateUserRequest(
        @UniqueUsername
        @NotEmpty
        String username,
        @NotEmpty
        String email,
        @NotEmpty
        String password,
        @NotEmpty
        String verifyPassword,
        @NotEmpty
        String name,
        @NotEmpty
        String surname,
        @NotEmpty
        String phone,
        @NotEmpty
        String adress,
        @NotNull
        int cp,
        @NotEmpty
        String gender,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date birthday,
        String profilePicture
) {
}
