package com.salesianos.geekhub.controller;


import com.salesianos.geekhub.dto.user.*;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.security.jwt.access.JwtService;
import com.salesianos.geekhub.security.jwt.refresh.RefreshToken;
import com.salesianos.geekhub.security.jwt.refresh.RefreshTokenRequest;
import com.salesianos.geekhub.security.jwt.refresh.RefreshTokenService;
import com.salesianos.geekhub.service.UserService;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users", description = "User controller")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;



    @Operation(summary = "Registra un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha realizado el registro del usuario",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "9fcc1058-d33d-4f26-878d-61dfee6f9c35",
                                                    "username": "wasd007"
                                                }                                    
                                            """
                            )}
                    )}),
    })
    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(user));
    }


    //Modificar, no se debe poder registrar un admin con permitAll
    @Operation(summary = "Registra un usuario con rol administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha realizado el registro del usuario administrador",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "73e52209-92b9-478b-b8f3-28af6b7a04fc",
                                                    "username": "qwerty2345"
                                                }                                
                                            """
                            )}
                    )}),
    })
    @PostMapping("/auth/register/admin")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUserAdmin(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(user));
    }


    @Operation(summary = "Inicia sesión en la cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha realizado el inicio de sesión",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LoginRequest.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": "9fcc1058-d33d-4f26-878d-61dfee6f9c35",
                                                    "username": "wasd007",
                                                    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5ZmNjMTA1OC1kMzNkLTRmMjYtODc4ZC02MWRmZWU2ZjljMzUiLCJpYXQiOjE3NDAxNDEzNjAsImV4cCI6MTc0MDE0MTY2MH0.8QBEpMiz8rA_fL2PeH6ixyy4wVRsy8bx3SCUHEpN_z4",
                                                    "refreshToken": "0269a079-3817-43fd-b3d5-1f29c7f5c7bd"
                                                }                              
                                            """
                            )}
                    )}),
    })
    //@PostAuthorize("returnObject.body.enabled==true")
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.username(),
                                loginRequest.password()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);

        RefreshToken refreshToken = refreshTokenService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(user, accessToken, refreshToken.getToken()));

    }


    @Operation(summary = "Refresca el token de sesión del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha generado un nuevo token de refresco",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RefreshTokenRequest.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                                        
                                            """
                            )}
                    )}),
    })
    @PostMapping("/auth/refresh/token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest req) {
        String token = req.refreshToken();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(refreshTokenService.refreshToken(token));

    }


    @Operation(summary = "Activa la cuenta de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha activado la cuenta",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ActivateAccountRequest.class)),
                            examples = {@ExampleObject(
                                    value = """
                                             {
                                                 "id": "9fcc1058-d33d-4f26-878d-61dfee6f9c35",
                                                 "username": "wasd007"
                                             }
                                            """
                            )}
                    )}),
    })
    @PostMapping("/activate/account/")
    public ResponseEntity<?> activateAccount(@RequestBody ActivateAccountRequest req) {
        String token = req.token();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(userService.activateAccount(token)));
    }


    @Operation(summary = "Obtiene los datos del usuario logeado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": "76d7e604-644e-49ca-93b8-5f11d3525f62",
                                                      "username": "wasd007"
                                                  }
                                              ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun usuario",
                    content = @Content),
    })
    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal User user) {
        System.out.println(user.getAuthorities());
        return UserResponse.of(user);
    }


    @Operation(summary = "Obtiene los datos del usuario con rol admin logeado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": "73e52209-92b9-478b-b8f3-28af6b7a04fc",
                                                     "username": "qwerty2345",
                                                     "email": "olga.i.valor.wu@gmail.com",
                                                     "password": "{bcrypt}$2a$10$UIQltsgg/bg711uDqxAXeeNpQ83iepsVxDu4PrNQGL1e./BFyASBa",
                                                     "name": "Juan",
                                                     "surname": "Garcia",
                                                     "phone": "678904532",
                                                     "address": "Calle Fuentes, 24",
                                                     "cp": 41006,
                                                     "gender": "male",
                                                     "birthday": "2003-02-21T00:00:00.000+00:00",
                                                     "profilePicture": null,
                                                     "bio": null,
                                                     "roles": [
                                                         "ADMIN"
                                                     ],
                                                     "enabled": true,
                                                     "activationToken": null,
                                                     "createdAt": "2025-02-21T12:32:48.534740Z",
                                                     "interests": [],
                                                     "authorities": [
                                                         {
                                                             "authority": "ROLE_ADMIN"
                                                         }
                                                     ],
                                                     "accountNonLocked": true,
                                                     "accountNonExpired": true,
                                                     "credentialsNonExpired": true
                                                 }
                                              ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun usuario",
                    content = @Content),
    })
    @GetMapping("/me/admin")
    public User adminMe(@AuthenticationPrincipal User user) {
        System.out.println(user.getAuthorities());
        return user;
    }


    @Operation(summary = "Edita un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario editado"),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el usuario ",
                    content = @Content),
    })
    @PutMapping("/user/{id}")
    public ResponseEntity<GetUserPrivateDataDto> edit(@RequestBody EditUserCmd editUserCmd, @PathVariable UUID id, @AuthenticationPrincipal User user) {

        if (!user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User updatedUser = userService.edit(editUserCmd, id);

        return ResponseEntity.ok(GetUserPrivateDataDto.of(updatedUser));
    }



    @Operation(summary = "Obtiene los datos del perfil visible de un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado datos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUserProfileDataDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                [
                                                    {
                                                          "username": "jdoe",
                                                          "name": "John",
                                                          "gender": "Male",
                                                          "profilePicture": "profile1.jpg",
                                                          "bio": "Biography example",
                                                          "interests": []
                                                      }
                                                  ]
                                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun usuario",
                    content = @Content),
    })
    @GetMapping("/user/{id}")
    public GetUserProfileDataDto getById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return GetUserProfileDataDto.of(user);


    }


    @Operation(summary = "Obtiene todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetUserProfileDataDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                [
                                                   {
                                                       "content": [
                                                           {
                                                               "username": "jdoe",
                                                               "name": "John",
                                                               "gender": "Male",
                                                               "profilePicture": "profile1.jpg",
                                                               "bio": "Biography example",
                                                               "interests": []
                                                           },
                                                           {
                                                               "username": "asmith",
                                                               "name": "Alice",
                                                               "gender": "Female",
                                                               "profilePicture": "profile2.jpg",
                                                               "bio": "Biography example",
                                                               "interests": []
                                                           },
                                                           {
                                                               "username": "bwhite",
                                                               "name": "Bob",
                                                               "gender": "Male",
                                                               "profilePicture": "profile3.jpg",
                                                               "bio": "Biography example",
                                                               "interests": []
                                                           },
                                                           {
                                                               "username": "dlee",
                                                               "name": "Diana",
                                                               "gender": "Female",
                                                               "profilePicture": "profile5.jpg",
                                                               "bio": "Biography example",
                                                               "interests": []
                                                           },
                                                           {
                                                               "username": "emartinez",
                                                               "name": "Ethan",
                                                               "gender": "Male",
                                                               "profilePicture": "profile6.jpg",
                                                               "bio": "Biography example",
                                                               "interests": []
                                                           }
                                                       ],
                                                       "pageable": {
                                                           "pageNumber": 0,
                                                           "pageSize": 5,
                                                           "sort": {
                                                               "empty": true,
                                                               "sorted": false,
                                                               "unsorted": true
                                                           },
                                                           "offset": 0,
                                                           "paged": true,
                                                           "unpaged": false
                                                       },
                                                       "last": false,
                                                       "totalPages": 2,
                                                       "totalElements": 10,
                                                       "first": true,
                                                       "size": 5,
                                                       "number": 0,
                                                       "sort": {
                                                           "empty": true,
                                                           "sorted": false,
                                                           "unsorted": true
                                                       },
                                                       "numberOfElements": 5,
                                                       "empty": false
                                                   }
                                                ]
                                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún usuario",
                    content = @Content),
    })
    @PostAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<Page<GetUserProfileDataDto>> getAll(Integer page,Integer size) {
        Page<User> usersPage = userService.findAll(0, 10);
        Page<GetUserProfileDataDto> usersDtoPage = usersPage.map(GetUserProfileDataDto::of);

        return ResponseEntity.ok(usersDtoPage);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        userService.delete(user);
        return ResponseEntity.noContent().build();

    }


}
