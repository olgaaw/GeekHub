package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.interest.EditInterestCmd;
import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.dto.post.PostResponseDto;
import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.InterestService;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interest")
@Tag(name = "Interests", description = "Interest controller")
public class InterstController {

    private final InterestService interestService;

    @Operation(summary = "Obtiene todos los intereses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetInterestDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                   {
                                                            "name": "Anime",
                                                            "picture": "anime_picture.jpg"
                                                        },
                                                        {
                                                            "name": "Videojuegos",
                                                            "picture": "videojuegos_picture.jpg"
                                                        },
                                                        {
                                                            "name": "Música",
                                                            "picture": "musica_picture.jpg"
                                                        },
                                                        {
                                                            "name": "Deportes",
                                                            "picture": "deportes_picture.jpg"
                                                        },
                                                        {
                                                            "name": "Cine",
                                                            "picture": "cine_picture.jpg"
                                                        },
                                                        {
                                                            "name": "Libros",
                                                            "picture": "libros_picture.jpg"
                                                        },
                                                        {
                                                            "name": "Viajes",
                                                            "picture": "viajes_picture.jpg"
                                                        },
                                               ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun interes",
                    content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<GetInterestDto>> getAll() {
        List<Interest> interests = interestService.findAll();
        List<GetInterestDto> response = interests.stream()
                .map(GetInterestDto::of)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Crea un interés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el interés",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetInterestDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "name": "World of Warcraft",
                                                "picture": "wow.jpg"
                                            }
                                            """
                            )}
                    )}),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GetInterestDto> createInterest(@RequestPart("interestDto") GetInterestDto interestParam, @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        Interest interest = interestService.create(interestParam, file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetInterestDto.of(interest));
    }


    @Operation(summary = "Edita un interés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el interés",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetInterestDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "name": "League of Legends",
                                                "picture": "lol.jpg"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Interés no encontrado",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GetInterestDto> editInterest(@PathVariable UUID id, @RequestPart("interestDto") EditInterestCmd editInterest,
                                                       @RequestPart(value = "file", required = false) MultipartFile file) {
        Interest interest = interestService.edit(id, editInterest, file);
        return ResponseEntity.ok(GetInterestDto.of(interest));
    }


    @Operation(summary = "Un usuario con rol ADMIN elimina un interés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado un interés",
                    content = @Content),
    })
    @PostAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{interestId}/delete")
    public ResponseEntity<?> deleteByIdAdmin(@PathVariable UUID interestId, @AuthenticationPrincipal User user){
        interestService.delete(interestId, user);
        return ResponseEntity.noContent().build();
    }
}

