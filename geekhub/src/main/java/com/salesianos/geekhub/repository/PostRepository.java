package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @EntityGraph(attributePaths = {"user", "images"})
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.dateP DESC")
    List<Post> findAllByUserId(UUID userId);



    @Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.images WHERE p.user.id = :userId ORDER BY p.dateP DESC",
            countQuery = "SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    Page<Post> findPostsByUserIdWithImages(@Param("userId") UUID userId, Pageable pageable);



    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.images WHERE p.user.username = :username ORDER BY p.dateP DESC")
    Page<Post> findPostsByUsername(@Param("username") String username, Pageable page);

    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Post findPostById(@Param("postId") UUID postId);

    @Query("SELECT l.user FROM Like l WHERE l.post.id = :postId")
    Page<User> findUsersLikedPost(@Param("postId") UUID postId, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "images"})
    @Query("""
            SELECT p FROM Post p
            JOIN Favourite f ON f.favouriteUser = p.user
            WHERE f.user.id = :userId
            ORDER BY p.dateP DESC
            """)
    List<Post> findTimelinePosts(@Param("userId") UUID userId);



}
