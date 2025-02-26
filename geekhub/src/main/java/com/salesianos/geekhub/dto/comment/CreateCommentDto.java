package com.salesianos.geekhub.dto.comment;

import com.salesianos.geekhub.model.Comment;
import jakarta.validation.constraints.NotBlank;


public record CreateCommentDto(
        @NotBlank(message = "{commentDto.content.notblank}")
         String content

) {
    public static CreateCommentDto of(Comment comment) {
        return new CreateCommentDto(
                comment.getContent()
        );
    }

}
