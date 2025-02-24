package com.salesianos.geekhub.dto;

import com.salesianos.geekhub.model.Like;

public record LikeDto(
        boolean isLiked

) {

    public static LikeDto of (Like like) {
        return new LikeDto(
                like.isLiked()
        );
    }

}
