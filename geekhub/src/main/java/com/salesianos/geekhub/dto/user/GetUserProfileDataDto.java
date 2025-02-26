package com.salesianos.geekhub.dto.user;

import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public record GetUserProfileDataDto(
        String username,
        String name,
        String gender,
        String profilePicture,
        String bio,
        Set<GetInterestDto> interests
) {
    public static GetUserProfileDataDto of(User user) {
        return new GetUserProfileDataDto(
                user.getUsername(),
                user.getName(),
                user.getGender(),
                user.getProfilePicture(),
                user.getBio(),
                user.getInterests().stream()
                        .map(GetInterestDto::of)
                        .collect(Collectors.toSet())
        );
    }

}

