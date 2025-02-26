package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.comment.CreateCommentDto;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.model.Comment;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.CommentRepository;
import com.salesianos.geekhub.repository.PostRepository;
import com.salesianos.geekhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createComment(UUID postId, CreateCommentDto commentDto, User user) {
        String username = user.getUsername();

        User user1 = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));

        Comment comment = Comment.builder()
                .content(commentDto.content())
                .user(user1)
                .post(post)
                .createdAt(Instant.now())
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> findByPostId (UUID postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No existen comentarios en el post");
        }

        return comments;

    }

}
