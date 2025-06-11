package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.favourite.FavouriteDto;
import com.salesianos.geekhub.dto.favourite.FavouriteUserDto;
import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.FavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Operation(summary = "Obtiene los usuarios que yo he marcado como favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de favoritos",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FavouriteUserDto.class)),
                            examples = @ExampleObject(value = """
                                    [
                                         {
                                             "id": "a7c449e4-1316-4ffc-a218-7a585fa128f3",
                                             "username": "user",
                                             "profilePicture": "https://t4.ftcdn.net/jpg/02/66/72/41/360_F_266724172_Iy8gdKgMa7XmrhYYxLCxyhx6J7070Pr8.jpg"
                                         },
                                         {
                                             "id": "a7c449e4-1316-4ffc-a218-7a585fa128f8",
                                             "username": "bwhite",
                                             "profilePicture": "profile3.jpg"
                                         }
                                     ]
                                    """)))
    })
    @GetMapping("/following/{userId}")
    public ResponseEntity<List<FavouriteUserDto>> getFollowingByUserId(@PathVariable UUID userId) {
        List<Favourite> favourites = favouriteService.getMyFavourites(userId);
        List<FavouriteUserDto> dtoList = favourites.stream()
                .map(f -> FavouriteUserDto.of(f.getFavouriteUser()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Obtiene los usuarios que me han marcado como favorito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de quienes me tienen como favorito",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FavouriteUserDto.class)),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "id": "b7c449e4-1316-4ffc-a218-7a585fa128f8",
                                            "username": "emartinez",
                                            "profilePicture": "profile6.jpg"
                                        },
                                        {
                                            "id": "a7c449e4-1316-4ffc-a218-7a585fa128f7",
                                            "username": "gthompson",
                                            "profilePicture": "profile8.jpg"
                                        }
                                    ]
                                    """)))
    })
    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<FavouriteUserDto>> getFollowersByUserId(@PathVariable UUID userId) {
        List<Favourite> favourites = favouriteService.getUsersWhoFavouritedMe(userId);
        List<FavouriteUserDto> dtoList = favourites.stream()
                .map(f -> FavouriteUserDto.of(f.getUser()))
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}