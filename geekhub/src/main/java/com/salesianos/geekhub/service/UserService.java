package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.user.CreateUserRequest;
import com.salesianos.geekhub.error.ActivationExpiredException;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.model.Role;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.UserRepository;
import com.salesianos.geekhub.util.SendGridMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendGridMailSender mailSender;


    @Value("${activation.duration}")
    private int activationDuration;

    public User createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.username())
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .name(createUserRequest.name())
                .surname(createUserRequest.surname())
                .phone(createUserRequest.phone())
                .address(createUserRequest.address())
                .cp(createUserRequest.cp())
                .gender(createUserRequest.gender())
                .birthday(createUserRequest.birthday())
                .roles(Set.of(Role.USER))
                .activationToken(generateRandomActivationCode())
                .build();

        System.out.println(user);

        try {
            mailSender.sendMail(createUserRequest.email(), "Activación de cuenta", user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }


        return userRepository.save(user);
    }

    public User createUserAdmin(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.username())
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .name(createUserRequest.name())
                .surname(createUserRequest.surname())
                .phone(createUserRequest.phone())
                .address(createUserRequest.address())
                .cp(createUserRequest.cp())
                .gender(createUserRequest.gender())
                .birthday(createUserRequest.birthday())
                .roles(Set.of(Role.ADMIN))
                .activationToken(generateRandomActivationCode())
                .build();

        System.out.println(user);

        try {
            mailSender.sendMail(createUserRequest.email(), "Activación de cuenta", user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }


        return userRepository.save(user);
    }

    public String generateRandomActivationCode() {
        return UUID.randomUUID().toString();
    }

    public User activateAccount(String token) {

        return userRepository.findByActivationToken(token)
                .filter(user -> ChronoUnit.MINUTES.between(Instant.now(), user.getCreatedAt()) - activationDuration < 0)
                .map(user -> {
                    user.setEnabled(true);
                    user.setActivationToken(null);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ActivationExpiredException("El código de activación no existe o ha caducado"));
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


}
