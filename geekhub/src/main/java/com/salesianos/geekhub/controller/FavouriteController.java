package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.favourite.FavouriteDto;
import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.FavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favourite")
public class FavouriteController {

    private final FavouriteService favouriteService;

    @Operation(summary = "Marcar a un usuario como favorito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorito añadido correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FavouriteDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": "49394dc1-b449-4613-8d3a-63e6b2bf3daa",
                                                "username": "admin",
                                                "favouriteUsername": "user"
                                            }
                                            """)))
    })
    @PostMapping("/add/{favouriteUserId}")
    public ResponseEntity<FavouriteDto> addFavourite(@PathVariable UUID favouriteUserId, @AuthenticationPrincipal User user) {
        Favourite favourite = favouriteService.addFavourite(favouriteUserId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(FavouriteDto.of(favourite));
    }

    @Operation(summary = "Eliminar un usuario de favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Favorito eliminado correctamente",
                    content = @Content)
    })
    @DeleteMapping("/remove/{favouriteUserId}")
    public ResponseEntity<?> removeFavourite(@PathVariable UUID favouriteUserId, @AuthenticationPrincipal User user) {
        favouriteService.removeFavourite(favouriteUserId, user);
        return ResponseEntity.noContent().build();
    }
}