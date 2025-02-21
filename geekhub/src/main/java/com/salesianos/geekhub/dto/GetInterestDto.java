package com.salesianos.geekhub.dto;

import com.salesianos.geekhub.model.Interest;

public record GetInterestDto (
        String name,
        String picture
){
    public static GetInterestDto of(Interest interest) {
        return new GetInterestDto(
                interest.getName(),
                interest.getPicture()

        );
    }
}
