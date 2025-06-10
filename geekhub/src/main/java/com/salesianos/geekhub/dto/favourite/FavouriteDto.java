package com.salesianos.geekhub.dto.favourite;

import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.Interest;

import java.util.UUID;

public record FavouriteDto(
        UUID id,
        String username,
        String favouriteUsername
) {
    public static FavouriteDto of(Favourite favourite) {
        return new FavouriteDto(
                favourite.getId(),
                favourite.getUser().getUsername(),
                favourite.getFavouriteUser().getUsername()
                );
    }
}
