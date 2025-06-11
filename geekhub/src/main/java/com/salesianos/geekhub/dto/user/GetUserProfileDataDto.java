package com.salesianos.geekhub.dto.user;

import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.model.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record GetUserProfileDataDto(
        UUID id,
        String username,
        String name,
        String gender,
        String profilePicture,
        String bio,
        int cp,
        Set<GetInterestDto> interests
) {
    public static GetUserProfileDataDto of(User user) {
        return new GetUserProfileDataDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getGender(),
                user.getProfilePicture(),
                user.getBio(),
                user.getCp(),
                user.getInterests().stream()
                        .map(GetInterestDto::of)
                        .collect(Collectors.toSet())
        );
    }

}

