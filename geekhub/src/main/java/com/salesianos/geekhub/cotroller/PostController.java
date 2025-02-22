package com.salesianos.geekhub.cotroller;

import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.dto.post.PostResponseDto;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.service.PostService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post controller")
public class PostController {

    private final PostService postService;


    @Operation(summary = "Crea un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el post correctamente",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f4",
                                                    "description": "Este es un post de prueba",
                                                    "date": "2025-02-22T11:39:21.307+00:00",
                                                    "images": []
                                                }                                    
                                            """
                            )}
                    )}),
    })
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> crear(@Valid @RequestBody CreatePostRequestDto postRequest) {
        Post post = postService.crearPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostResponseDto.of(post));
    }
}
