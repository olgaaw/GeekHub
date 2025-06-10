package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavouriteRepository extends JpaRepository<Favourite, UUID> {

    Optional<Favourite> findByUserAndFavouriteUser(User user, User favouriteUser);

    void deleteByUserAndFavouriteUser(User user, User favouriteUser);
}
