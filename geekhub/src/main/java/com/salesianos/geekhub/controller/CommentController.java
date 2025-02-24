package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.CommentDto;
import com.salesianos.geekhub.model.Comment;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "Comments", description = "Comment controller")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Crea un comentario en un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el comentario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CommentDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "content": "Este es un comentario",
                                                "username": "user1",
                                                "createdAt": "2025-02-23T17:13:22.676460600Z"
                                            }
                                            """
                            )}
                    )}),
    })
    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable UUID postId,@Valid @RequestBody CommentDto commentDto, @AuthenticationPrincipal User user) {
        Comment comment = commentService.createComment(postId, commentDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentDto.of(comment));
    }
}
