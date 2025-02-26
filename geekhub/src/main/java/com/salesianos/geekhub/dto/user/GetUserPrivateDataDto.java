package com.salesianos.geekhub.dto.user;

import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.model.User;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record GetUserPrivateDataDto(
        String username,
        String email,
        String name,
        String surname,
        String phone,
        String address,
        int cp,
        Date birthday,
        String profilePicture,
        String bio,
        Instant createdAt,
        Set<GetInterestDto> interests
) {

    public static GetUserPrivateDataDto of (User user) {
        return new GetUserPrivateDataDto(
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getAddress(),
                user.getCp(),
                user.getBirthday(),
                user.getProfilePicture(),
                user.getBio(),
                user.getCreatedAt(),
                user.getInterests().stream()
                        .map(GetInterestDto::of)
                        .collect(Collectors.toSet())



        );
    }
}
