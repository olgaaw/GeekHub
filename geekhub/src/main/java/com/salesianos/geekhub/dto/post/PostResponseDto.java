package com.salesianos.geekhub.dto.post;

import com.salesianos.geekhub.model.Post;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PostResponseDto(
        UUID userId,
        String username,
        String description,
        Date date,
        List<ImageRequestDto> images
) {

    public static PostResponseDto of(Post post) {
        return new PostResponseDto(
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getDescription(),
                post.getDateP(),
                post.getImages().stream()
                        .map(image -> new ImageRequestDto(image.getImageUrl()))
                        .collect(Collectors.toList())
        );
    }
}
