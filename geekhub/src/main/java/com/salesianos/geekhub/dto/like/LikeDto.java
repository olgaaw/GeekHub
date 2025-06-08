package com.salesianos.geekhub.dto.like;

import com.salesianos.geekhub.model.Like;

import java.util.UUID;

public record LikeDto(
        UUID id,
        boolean isLiked

) {

    public static LikeDto of (Like like) {
        return new LikeDto(
                like.getId(),
                like.isLiked()
        );
    }

}
