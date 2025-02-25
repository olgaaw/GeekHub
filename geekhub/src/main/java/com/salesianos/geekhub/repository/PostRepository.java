package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByUserId(UUID userId);

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.images WHERE p.user.id = :userId")
    List<Post> findPostsByUserIdWithImages(@Param("userId") UUID userId);


    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.images WHERE p.user.username = :username")
    List<Post> findPostsByUsername(@Param("username") String username);

    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Post findPostById(@Param("postId") UUID postId);

    @Query("SELECT l.user FROM Like l WHERE l.post.id = :postId")
    List<User> findUsersLikedPost(@Param("postId") UUID postId);






}
