package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.like.LikeDto;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.model.Comment;
import com.salesianos.geekhub.model.Like;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.LikeRepository;
import com.salesianos.geekhub.repository.PostRepository;
import com.salesianos.geekhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Like addLike(UUID postId, User user) {
        String username = user.getUsername();

        User user1 = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));

        Like like = Like.builder()
                .user(user1)
                .post(post)
                .createdAt(Instant.now())
                .isLiked(true)
                .build();

        return likeRepository.save(like);
    }


    public void delete(UUID likeId, User user) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("No existe like con el id"+likeId));

        if (like.getUser().getId().equals(user.getId())) {
            likeRepository.deleteById(likeId);
        } else {
            throw new RuntimeException("Error al eliminar el like. El like no pertenece al usuario loggeado");
        }
    }


}
