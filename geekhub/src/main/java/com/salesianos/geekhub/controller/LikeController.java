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
@RequestMapping("/post")
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
    @PostMapping("/{postId}/like")
    public ResponseEntity<LikeDto> createLike(@PathVariable UUID postId, @RequestBody LikeDto likeDto , @AuthenticationPrincipal User user) {
        Like like = likeService.addLike(postId, likeDto ,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(LikeDto.of(like));
    }
}
