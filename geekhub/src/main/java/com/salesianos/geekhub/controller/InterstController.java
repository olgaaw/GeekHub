package com.salesianos.geekhub.controller;

import com.salesianos.geekhub.dto.interest.EditInterestCmd;
import com.salesianos.geekhub.dto.interest.GetInterestDto;
import com.salesianos.geekhub.model.Interest;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interest")
@Tag(name = "Interests", description = "Interest controller")
public class InterstController {

    private final InterestService interestService;

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
    public ResponseEntity<GetInterestDto> createInterest(@RequestBody GetInterestDto interestDto) {
        Interest interest = interestService.create(interestDto);
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
                                                "name": "World of Warcraft",
                                                "picture": "wow.jpg"
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
    public ResponseEntity<GetInterestDto> editInterest(@PathVariable UUID id, @RequestBody EditInterestCmd editInterest) {
        Interest interest = interestService.edit(id, editInterest);
        return ResponseEntity.ok(GetInterestDto.of(interest));
    }
}

