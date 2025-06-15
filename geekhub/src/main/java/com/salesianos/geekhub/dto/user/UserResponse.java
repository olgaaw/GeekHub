package com.salesianos.geekhub.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianos.geekhub.model.Role;
import com.salesianos.geekhub.model.User;

import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        Set<Role> roles,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String token,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String refreshToken
) {
    public static UserResponse of (User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRoles(),null, null);
    }

    public static UserResponse of (User user, String token, String refreshToken) {
        return new UserResponse(user.getId(), user.getUsername(),user.getRoles(), token, refreshToken);
    }
}
