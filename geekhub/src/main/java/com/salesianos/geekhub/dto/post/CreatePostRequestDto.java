package com.salesianos.geekhub.dto.post;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record CreatePostRequestDto(
        UUID userId,
        @NotBlank
        String description,
        List<ImageRequestDto> images,
        Date fechaPublicacion
){


}

