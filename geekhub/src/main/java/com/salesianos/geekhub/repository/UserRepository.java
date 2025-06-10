package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {

    Optional<User> findFirstByUsername(String username);

    Optional<User> findByActivationToken(String activationToken);

    boolean existsByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.interests WHERE u.id = :id")
    Optional<User> findByIdWithInterests(@Param("id") UUID id);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.interests")
    List<User> findAllWithInterests();

    @Override
    @EntityGraph(attributePaths = {"interests"})
    Page<User> findAll(Pageable pageable);
}
