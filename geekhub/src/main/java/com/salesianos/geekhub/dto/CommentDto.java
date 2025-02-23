package com.salesianos.geekhub.dto;

import com.salesianos.geekhub.model.Comment;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record CommentDto(
        @NotBlank(message = "{commentDto.content.notblank}")
         String content,
         String username,
         Instant createdAt

) {
    public static CommentDto of(Comment comment) {
        return new CommentDto(
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getCreatedAt()
        );
    }

}
