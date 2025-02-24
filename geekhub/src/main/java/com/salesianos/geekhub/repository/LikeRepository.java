package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
}
