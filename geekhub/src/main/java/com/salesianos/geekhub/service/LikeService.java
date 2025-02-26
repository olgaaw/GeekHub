package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.like.LikeDto;
import com.salesianos.geekhub.error.UserNotFoundException;
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

    public Like addLike(UUID postId, LikeDto likeDto, User user) {
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


}
