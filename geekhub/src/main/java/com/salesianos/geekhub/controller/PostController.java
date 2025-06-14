package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.PaginationDto;
import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.dto.post.GetPostDetailsDto;
import com.salesianos.geekhub.dto.post.PostResponseDto;
import com.salesianos.geekhub.dto.user.GetUserProfileDataDto;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/")
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
    @PostMapping
    public ResponseEntity<PostResponseDto> crear(@RequestPart(value = "files", required = false) MultipartFile[] files, @Valid @RequestPart("post") CreatePostRequestDto createPostRequestDto, @AuthenticationPrincipal User user) {
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
    @GetMapping("user/{userId}/page")
    public ResponseEntity<Page<PostResponseDto>> getAllByUserId(@PathVariable UUID userId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        Page<Post> postsPage = postService.findAllByUserId(userId, page, size);
        Page<PostResponseDto> postsDtoPage = postsPage.map(PostResponseDto::of);

        return ResponseEntity.ok(postsDtoPage);
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
    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostResponseDto>> getAllByUserIdNoPagination(@PathVariable UUID userId) {
        List<Post> posts = postService.findAllByUserId(userId);
        List<PostResponseDto> response = posts.stream()
                .map(PostResponseDto::of)
                .toList();

        return ResponseEntity.ok(response);
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
    @GetMapping("username/{username}")
    public ResponseEntity<PaginationDto<PostResponseDto>> getAllByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Page<Post> postsPage = postService.findAllByUsername(username, page, size);
        Page<PostResponseDto> postsDtoPage = postsPage.map(PostResponseDto::of);

        return ResponseEntity.ok(PaginationDto.of(postsDtoPage));
    }


    @Operation(summary = "Obtiene los detalles de un post junto a su numero de likes y comentarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetPostDetailsDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            
                                                {
                                                     "post": {
                                                         "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f4",
                                                         "username": "jdoe",
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
                                                     "commentNum": 3,
                                                     "commentLike": 1
                                                 }
                                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun post",
                    content = @Content),
    })
    @GetMapping("{id}")
    public ResponseEntity<GetPostDetailsDto> getDetailsById(@PathVariable UUID id) {
        Post post = postService.findDetailsById(id);

        return ResponseEntity.ok(GetPostDetailsDto.of(post));

    }

    @Operation(summary = "Obtiene los usuarios que han dado like a un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUserProfileDataDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "username": "user1",
                                                    "name": "Juan",
                                                    "gender": "male",
                                                    "profilePicture": null,
                                                    "bio": null,
                                                    "interests": []
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun post",
                    content = @Content),
    })
    @GetMapping("/{postId}/likes")
    public PaginationDto<GetUserProfileDataDto> getUsersLikedPost(
            @PathVariable UUID postId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Page<User> usersPage = postService.getUsersLikedPost(postId, page, size);
        Page<GetUserProfileDataDto> usersDtoPage = usersPage.map(GetUserProfileDataDto::of);

        return PaginationDto.of(usersDtoPage);
    }

    @Operation(summary = "Elimina un post del usuario loggeado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado un post",
                    content = @Content),
    })
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<?> deleteByUser(@PathVariable UUID postId, @AuthenticationPrincipal User user){
        postService.deleteByUser(postId, user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene los posts de los usuarios marcados como favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "userId": "b7c449e4-1316-4ffc-a218-7a585fa128f8",
                                                    "username": "emartinez",
                                                    "profilePicture": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSd3IbpKVGNFbBqbvQOi_XzHjk11OYToxtSw&s",
                                                    "id": "eb94e0c1-9264-48b4-bb79-ffaf759ac6ba",
                                                    "description": "Este es un post de prueba de Ethan Martinez",
                                                    "date": "2025-02-23T17:15:00.000+00:00",
                                                    "images": [
                                                        {
                                                            "imageUrl": "https://www.nintendo.com/eu/media/images/10_share_images/games_15/nintendo_7/SI_N64_LegendOfZeldaOcarinaOfTime.jpg"
                                                        }
                                                    ]
                                                },
                                                {
                                                    "userId": "a7c449e4-1316-4ffc-a218-7a585fa128f9",
                                                    "username": "dlee",
                                                    "profilePicture": "https://urgenciesveterinaries.com/wp-content/uploads/2023/09/survet-gato-caida-pelo-01.jpeg",
                                                    "id": "aeb6a582-8d9b-45c3-8a4b-9084823d2431",
                                                    "description": "Este es un post de prueba de Diana Lee",
                                                    "date": "2025-02-23T15:00:00.000+00:00",
                                                    "images": [
                                                        {
                                                            "imageUrl": "https://www.nintendo.com/eu/media/images/10_share_images/games_15/nintendo_7/SI_N64_LegendOfZeldaOcarinaOfTime.jpg"
                                                        }
                                                    ]
                                                }   
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun post",
                    content = @Content),
    })
    @GetMapping("timeline")
    public List<PostResponseDto> getTimeline(@AuthenticationPrincipal User user) {
        return postService.getTimelinePosts(user.getId())
                .stream()
                .map(PostResponseDto::of)
                .toList();
    }


}
