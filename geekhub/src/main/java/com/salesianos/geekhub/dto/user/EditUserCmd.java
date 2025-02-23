package com.salesianos.geekhub.dto.user;

import com.salesianos.geekhub.dto.GetInterestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
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
