package com.salesianos.geekhub.dto;

import com.salesianos.geekhub.model.Comment;

import java.time.Instant;

public record GetCommentDto(
        String username,
        String profilePicture,
        String content,
        Instant createdAt
) {

    public static GetCommentDto of(Comment comment) {
        return new GetCommentDto(
                comment.getUser().getUsername(),
                comment.getUser().getProfilePicture(),
                comment.getContent(),
                comment.getCreatedAt()

        );
    }
}
