package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.dto.post.PostResponseDto;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
                                                    "userId": "c6bc7533-fa3b-4e1c-b2ba-e9260888c4e4",
                                                    "description": "Post de prueba con subida de ficheros",
                                                    "date": "2025-02-24T22:41:16.540+00:00",
                                                    "images": [
                                                        {
                                                            "imageUrl": "http://localhost:8080/download/gato_876543.jpg"
                                                        }
                                                    ]
                                                }
                                            """
                            )}
                    )}),
    })
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> crear(@RequestPart("files") MultipartFile[] files, @Valid @RequestPart("post") CreatePostRequestDto createPostRequestDto, @AuthenticationPrincipal User user) {
        Post post = postService.crearPost(createPostRequestDto, files, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(PostResponseDto.of(post));
    }

    @Operation(summary = "Obtiene los posts de un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                   {
                                                       "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f4",
                                                       "description": "Este es un post de prueba 2",
                                                       "date": "2025-02-22T11:00:00.000+00:00",
                                                       "images": [
                                                           {
                                                               "imageUrl": "https://example.com/imagen1.jpg"
                                                           },
                                                           {
                                                               "imageUrl": "https://example.com/imagen2.jpg"
                                                           }
                                                       ]
                                                   },
                                                   {
                                                       "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f4",
                                                       "description": "Este es un post de prueba de John Doe",
                                                       "date": "2025-02-23T09:00:00.000+00:00",
                                                       "images": [
                                                           {
                                                               "imageUrl": "https://example.com/jdoe_post_image.jpg"
                                                           }
                                                       ]
                                                   }
                                               ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun post para el usuario",
                    content = @Content),
    })
    @GetMapping("/post/user/{userId}")
    public List<PostResponseDto> getAllByUserId(@PathVariable UUID userId) {
        return postService.findAllByUserId(userId)
                .stream()
                .map(PostResponseDto::of)
                .toList();
    }


    @Operation(summary = "Obtiene los posts de un usuario por su username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f4",
                                                    "description": "Este es un post de prueba 2",
                                                    "date": "2025-02-22T11:00:00.000+00:00",
                                                    "images": [
                                                        {
                                                            "imageUrl": "https://example.com/imagen1.jpg"
                                                        },
                                                        {
                                                            "imageUrl": "https://example.com/imagen2.jpg"
                                                        }
                                                    ]
                                                },
                                                {
                                                    "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f4",
                                                    "description": "Este es un post de prueba de John Doe",
                                                    "date": "2025-02-23T09:00:00.000+00:00",
                                                    "images": [
                                                        {
                                                            "imageUrl": "https://example.com/jdoe_post_image.jpg"
                                                        }
                                                    ]
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun post para el usuario",
                    content = @Content),
    })
    @GetMapping("/post/username/{username}")
    public List<PostResponseDto> getAllByUsername(@PathVariable String username) {
        return postService.findAllByUsername(username)
                .stream()
                .map(PostResponseDto::of)
                .toList();
    }
}
