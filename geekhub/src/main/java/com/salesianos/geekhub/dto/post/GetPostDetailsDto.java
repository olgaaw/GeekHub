package com.salesianos.geekhub.dto.post;

import com.salesianos.geekhub.model.Post;

public record GetPostDetailsDto(
        PostResponseDto post,
        int commentNum,
        int commentLike
) {
    public static GetPostDetailsDto of(Post post) {
        return new GetPostDetailsDto(
                PostResponseDto.of(post),
                post.getComments().size(),
                post.getLikes().size()
        );
    }
}
