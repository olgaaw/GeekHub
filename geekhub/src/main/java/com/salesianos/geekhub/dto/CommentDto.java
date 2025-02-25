package com.salesianos.geekhub.dto;

import com.salesianos.geekhub.model.Comment;
import jakarta.validation.constraints.NotBlank;


public record CommentDto(
        @NotBlank(message = "{commentDto.content.notblank}")
         String content

) {
    public static CommentDto of(Comment comment) {
        return new CommentDto(
                comment.getContent()
        );
    }

}
