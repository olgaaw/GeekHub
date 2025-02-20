package com.salesianos.geekhub.cotroller;

import com.salesianos.geekhub.dto.CreateUserRequest;
import com.salesianos.geekhub.dto.GetUserProfileDataDto;
import com.salesianos.geekhub.dto.UserResponse;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);
        System.out.println(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of(user));
    }

    @GetMapping("/user/{id}")
    public GetUserProfileDataDto getById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return GetUserProfileDataDto.of(user);


    }
}
