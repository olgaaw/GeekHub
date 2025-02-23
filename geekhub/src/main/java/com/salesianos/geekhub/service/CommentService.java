package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.CommentDto;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.model.Comment;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.CommentRepository;
import com.salesianos.geekhub.repository.PostRepository;
import com.salesianos.geekhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createComment(UUID postId, CommentDto commentDto, UserDetails userDetails) {
        String username = userDetails.getUsername();

        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));

        Comment comment = Comment.builder()
                .content(commentDto.content())
                .user(user)
                .post(post)
                .createdAt(Instant.now())
                .build();

        return commentRepository.save(comment);
    }

}
