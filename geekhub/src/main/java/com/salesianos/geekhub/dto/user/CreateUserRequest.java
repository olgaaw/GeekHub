package com.salesianos.geekhub.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianos.geekhub.validation.FieldsValueMatch;
import com.salesianos.geekhub.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
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
        @NotBlank(message = "{createUserRequest.username.notblank}")
        String username,
        @NotBlank(message = "{createUserRequest.email.notblank}")
        String email,
        @NotBlank(message = "{createUserRequest.password.notblank}")
        String password,
        @NotBlank(message = "{createUserRequest.verifyPassword.notblank}")
        String verifyPassword,
        @NotBlank(message = "{createUserRequest.name.notblank}")
        String name,
        @NotBlank(message = "{createUserRequest.surname.notblank}")
        String surname,
        @NotBlank(message = "{createUserRequest.phone.notblank}")
        String phone,
        @NotBlank(message = "{createUserRequest.address.notblank}")
        String address,
        @NotNull
        int cp,
        @NotBlank(message = "{createUserRequest.gender.notblank}")
        String gender,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date birthday,
        String profilePicture
) {
}
