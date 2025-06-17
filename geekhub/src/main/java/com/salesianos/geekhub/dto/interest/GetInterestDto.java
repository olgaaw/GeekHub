package com.salesianos.geekhub.dto.interest;

import com.salesianos.geekhub.model.Interest;

import java.util.UUID;

public record GetInterestDto (
        UUID id,
        String name,
        String picture
){
    public static GetInterestDto of(Interest interest) {
        return new GetInterestDto(
                interest.getId(),
                interest.getName(),
                interest.getPicture()

        );
    }
}
