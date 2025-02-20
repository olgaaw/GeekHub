package com.salesianos.geekhub.cotroller;

import com.salesianos.geekhub.dto.CreateUserRequest;
import com.salesianos.geekhub.dto.UserResponse;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
