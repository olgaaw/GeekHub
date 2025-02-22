package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
