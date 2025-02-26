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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Transactional
    public Page<Comment> findByPostId(UUID postId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentRepository.findCommentsByPostIdPageable(postId, pageable);

        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No existen comentarios en el post con id " + postId);
        }

        return comments;
    }

    public void deletebyUser(UUID id, User user) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe comentario con el id"+id));

        if (comment.getUser().getId().equals(user.getId())) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Error al eliminar el comentario. El comentario no pertenece al usuario loggeado");
        }
    }


    public void deleteByAdmin(UUID id, User user) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe comentario con el id " + id));

        commentRepository.deleteById(id);

    }




}
