package com.salesianos.geekhub.dto.post;

import com.salesianos.geekhub.model.Post;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreatePostRequestDto(
        @NotBlank(message = "{createPostRequestDto.description.notblank}")
        String description,
        List<ImageRequestDto> images
) {


        public static CreatePostRequestDto of(Post post, List<ImageRequestDto> imageDtos) {
                return new CreatePostRequestDto(post.getDescription(), imageDtos);
        }
}
