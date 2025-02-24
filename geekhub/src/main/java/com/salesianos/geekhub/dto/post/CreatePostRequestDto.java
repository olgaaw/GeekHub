package com.salesianos.geekhub.dto.post;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record CreatePostRequestDto(
        @NotBlank(message = "{createPostRequestDto.description.notblank}")
        String description,
        List<ImageRequestDto> images
){


}

