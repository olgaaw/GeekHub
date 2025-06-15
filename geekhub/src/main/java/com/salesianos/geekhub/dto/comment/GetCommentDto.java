package com.salesianos.geekhub.dto.comment;

import com.salesianos.geekhub.model.Comment;

import java.time.Instant;
import java.util.UUID;

public record GetCommentDto(
        UUID userId,
        UUID commentId,
        String username,
        String profilePicture,
        String content,
        Instant createdAt
) {

    public static GetCommentDto of(Comment comment) {
        return new GetCommentDto(
                comment.getUser().getId(),
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getUser().getProfilePicture(),
                comment.getContent(),
                comment.getCreatedAt()

        );
    }
}
