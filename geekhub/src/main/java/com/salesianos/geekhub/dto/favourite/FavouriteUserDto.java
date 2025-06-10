package com.salesianos.geekhub.dto.favourite;

import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.User;

import java.util.UUID;

public record FavouriteUserDto(
        UUID id,
        String username,
        String profilePicture
) {
    public static FavouriteUserDto of(User user) {
        return new FavouriteUserDto(
                user.getId(),
                user.getUsername(),
                user.getProfilePicture()
        );
    }
}
