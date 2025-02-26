package com.salesianos.geekhub.dto.user;

import com.salesianos.geekhub.dto.interest.GetInterestDto;

import java.util.Date;
import java.util.Set;

public record EditUserCmd(
        String username,
        String email,
        String name,
        String surname,
        String phone,
        String address,
        Integer cp,
        Date birthday,
        String profilePicture,
        String bio,
        Set<GetInterestDto> interests
) {
}
