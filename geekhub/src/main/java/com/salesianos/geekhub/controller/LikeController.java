package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.like.LikeDto;
import com.salesianos.geekhub.model.Like;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Likes", description = "Like controller")
public class LikeController {

    private final LikeService likeService;


    @Operation(summary = "Añade un like a un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha añadido el like correctamente",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LikeDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "isLiked": true
                                                }
                                            """
                            )}
                    )}),
    })
    @PostMapping("/post/{postId}/like")
    public ResponseEntity<LikeDto> createLike(@PathVariable UUID postId, @RequestBody LikeDto likeDto , @AuthenticationPrincipal User user) {
        Like like = likeService.addLike(postId, likeDto ,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(LikeDto.of(like));
    }

    @Operation(summary = "Elimina un like del usuario loggeado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado un like",
                    content = @Content),
    })
    @DeleteMapping("/like/{likeId}/deletebyUser")
    public ResponseEntity<?> deleteById(@PathVariable UUID likeId, @AuthenticationPrincipal User user){
        likeService.delete(likeId, user);
        return ResponseEntity.noContent().build();
    }
}
