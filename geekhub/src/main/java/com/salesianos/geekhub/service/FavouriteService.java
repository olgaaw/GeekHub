package com.salesianos.geekhub.service;

import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.FavouriteRepository;
import com.salesianos.geekhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;

    public Favourite addFavourite(UUID favouriteUserId, User user) {
        String username = user.getUsername();

        User user1 = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        User favouriteUser = userRepository.findById(favouriteUserId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario favorito no encontrado"));

        Optional<Favourite> existing = favouriteRepository.findByUserAndFavouriteUser(user1, favouriteUser);
        if (existing.isPresent()) {
            throw new IllegalStateException("El usuario ya está marcado como favorito");
        }

        Favourite favourite = Favourite.builder()
                .user(user1)
                .favouriteUser(favouriteUser)
                .build();

        return favouriteRepository.save(favourite);
    }

    @Transactional
    public void removeFavourite(UUID favouriteUserId, User user) {
        String username = user.getUsername();

        User currentUser = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        User favouriteUser = userRepository.findById(favouriteUserId)
                .orElseThrow(() -> new RuntimeException("Usuario a quitar de favoritos no encontrado"));

        favouriteRepository.deleteByUserAndFavouriteUser(currentUser, favouriteUser);
    }


}
