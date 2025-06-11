package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Favourite;
import com.salesianos.geekhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, UUID> {

    Optional<Favourite> findByUserAndFavouriteUser(User user, User favouriteUser);

    void deleteByUserAndFavouriteUser(User user, User favouriteUser);

    @Query("SELECT f FROM Favourite f JOIN FETCH f.favouriteUser WHERE f.user.id = :userId")
    List<Favourite> findAllByUserIdWithFavouriteUser(@Param("userId") UUID userId);

    @Query("SELECT f FROM Favourite f JOIN FETCH f.user WHERE f.favouriteUser.id = :userId")
    List<Favourite> findAllByFavouriteUserIdWithUser(@Param("userId") UUID userId);



}
