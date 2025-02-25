package com.salesianos.geekhub.repository;

import com.salesianos.geekhub.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("""
                SELECT c FROM Comment c 
                JOIN FETCH c.user 
                WHERE c.post.id = :postId
            """)
    List<Comment> findCommentsByPostId(@Param("postId") UUID postId);


}
