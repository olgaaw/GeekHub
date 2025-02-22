package com.salesianos.geekhub.dto.post;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record PostRequestDto(
        UUID userId,
        String description,
        List<ImageRequestDto> images,
        Date fechaPublicacion
){


}

