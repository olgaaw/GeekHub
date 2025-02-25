package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    @Query("""
                SELECT l 
                FROM Like l
                WHERE l.post.id = :postId
            """)
    List<Like> findLikesByPostId(@Param("postId") UUID postId);

}
