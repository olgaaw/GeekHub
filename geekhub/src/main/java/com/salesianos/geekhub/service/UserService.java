package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.user.CreateUserRequest;
import com.salesianos.geekhub.dto.user.EditUserCmd;
import com.salesianos.geekhub.error.ActivationExpiredException;
import com.salesianos.geekhub.error.InterestNotFoundException;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.files.exception.StorageException;
import com.salesianos.geekhub.files.model.FileMetadata;
import com.salesianos.geekhub.files.service.StorageService;
import com.salesianos.geekhub.model.Interest;
import com.salesianos.geekhub.model.Role;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.query.UserSpecificationBuilder;
import com.salesianos.geekhub.repository.InterestRepository;
import com.salesianos.geekhub.repository.UserRepository;
import com.salesianos.geekhub.security.jwt.refresh.RefreshTokenRepository;
import com.salesianos.geekhub.util.SearchCriteria;
import com.salesianos.geekhub.util.SendGridMailSender;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendGridMailSender mailSender;
    private final InterestRepository interestRepository;
    private final StorageService storageService;


    @Value("${activation.duration}")
    private int activationDuration;
    private final RefreshTokenRepository refreshTokenRepository;

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
            String texto = "Su código de activación es: ";
            mailSender.sendMail(createUserRequest.email(), "Activación de cuenta", texto+user.getActivationToken());
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

        System.out.println(user.getAuthorities());

        try {
            mailSender.sendMail(createUserRequest.email(), "Activación de cuenta", user.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al enviar el email de activación");
        }


        return userRepository.save(user);
    }



    public String generateRandomActivationCode() {
        Random random = new Random();
        int randomCode = 1000 + random.nextInt(9000);
        return String.valueOf(randomCode);
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


    @Transactional
    public User findById(UUID id) {
        return userRepository.findByIdWithInterests(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public Page<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> result = userRepository.findAll(pageable);

        if (result.isEmpty()) {
            throw new UserNotFoundException();
        }

        return result;
    }



    public User edit(EditUserCmd editUserCmd, User userP, MultipartFile file) {
        User user = userRepository.findByIdWithInterests(userP.getId()).orElseThrow(() -> new UserNotFoundException(userP.getId()));
        user.getInterests().size();

        if (editUserCmd.username() != null) {
            user.setUsername(editUserCmd.username());
        }
        if (editUserCmd.email() != null) {
            user.setEmail(editUserCmd.email());
        }
        if (editUserCmd.name() != null) {
            user.setName(editUserCmd.name());
        }
        if (editUserCmd.surname() != null) {
            user.setSurname(editUserCmd.surname());
        }
        if (editUserCmd.phone() != null) {
            user.setPhone(editUserCmd.phone());
        }
        if (editUserCmd.address() != null) {
            user.setAddress(editUserCmd.address());
        }
        if (editUserCmd.cp() != null) {
            user.setCp(editUserCmd.cp());
        }
        if (editUserCmd.birthday() != null) {
            user.setBirthday(editUserCmd.birthday());
        }
        if (editUserCmd.bio() != null) {
            user.setBio(editUserCmd.bio());
        }

        if (file != null && !file.isEmpty()) {
            FileMetadata fileMetadata = storageService.store(file);
            String imageUrl = fileMetadata.getURL();
            if (imageUrl != null) {
                user.setProfilePicture(imageUrl);
            } else {
                throw new StorageException("Error al subir la imagen de perfil");
            }
        } else if (editUserCmd.profilePicture() != null) {
            user.setProfilePicture(editUserCmd.profilePicture());
        }

        if (editUserCmd.interests() != null && !editUserCmd.interests().isEmpty()) {
            Set<Interest> interests = editUserCmd.interests().stream()
                    .map(interestDto -> interestRepository.findByName(interestDto.name())
                            .orElseThrow(() -> new InterestNotFoundException("Interés no encontrado: " + interestDto.name())))
                    .collect(Collectors.toSet());

            user.setInterests(interests);
        }

        return userRepository.save(user);
    }


    @Transactional
    public void delete(User user) {
        if(user != null) {
            refreshTokenRepository.deleteByUser(user);
            userRepository.delete(user);
        }
    }



    @Transactional
    public List<User> search(List<SearchCriteria> searchCriteriaList) {

        UserSpecificationBuilder userSpecificationBuilder
                = new UserSpecificationBuilder(searchCriteriaList);

        Specification<User> where = userSpecificationBuilder.build();

        return userRepository.findAll(where);
    }

}



