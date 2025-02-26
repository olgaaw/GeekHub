package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.PaginationDto;
import com.salesianos.geekhub.dto.comment.CreateCommentDto;
import com.salesianos.geekhub.dto.comment.GetCommentDto;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comments", description = "Comment controller")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Crea un comentario en un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el comentario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CreateCommentDto.class)),
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
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<GetCommentDto> createComment(@PathVariable UUID postId, @Valid @RequestBody CreateCommentDto commentDto, @AuthenticationPrincipal User user) {
        Comment comment = commentService.createComment(postId, commentDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetCommentDto.of(comment));
    }


    @Operation(summary = "Obtiene los comnetarios del un post por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetCommentDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                [
                                                    {
                                                        "username": "user1",
                                                        "profilePicture": "profilePicture.jpg",
                                                        "content": "Este es un comentario 1",
                                                        "createdAt": "2025-02-25T11:42:53.060183Z"
                                                    },
                                                    {
                                                        "username": "user1",
                                                        "profilePicture": "profilePicture.jpg",
                                                        "content": "Este es un comentario 1",
                                                        "createdAt": "2025-02-25T11:42:54.231202Z"
                                                    },
                                                    {
                                                        "username": "user1",
                                                        "profilePicture": "profilePicture.jpg",
                                                        "content": "Este es un comentario 1",
                                                        "createdAt": "2025-02-25T11:42:55.400480Z"
                                                    }
                                                ]
                                             """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun post con ese id",
                    content = @Content),
    })
    @GetMapping("/post/{postId}/comment/detail")
    public ResponseEntity<PaginationDto<GetCommentDto>> getAllByPostId(@PathVariable UUID postId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        Page<Comment> commentsPage = commentService.findByPostId(postId, page, size);
        Page<GetCommentDto> commentsDtoPage = commentsPage.map(GetCommentDto::of);

        return ResponseEntity.ok(PaginationDto.of(commentsDtoPage));
    }

    @Operation(summary = "Elimina un comentario del usuario loggeado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado un comentario",
                    content = @Content),
    })
    @DeleteMapping("/comment/{commentId}/delete")
    public ResponseEntity<?> deleteById(@PathVariable UUID commentId, @AuthenticationPrincipal User user){
        commentService.delete(commentId, user);
        return ResponseEntity.noContent().build();
    }


}
