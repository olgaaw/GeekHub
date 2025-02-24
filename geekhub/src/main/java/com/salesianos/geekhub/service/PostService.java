package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.post.ImageRequestDto;
import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.model.Image;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.model.User;
import com.salesianos.geekhub.repository.ImageRepository;
import com.salesianos.geekhub.repository.PostRepository;
import com.salesianos.geekhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public Post crearPost(CreatePostRequestDto postRequest, User user) {

        String username = user.getUsername();

        User user1 = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Post post = Post.builder()
                .user(user1)
                .description(postRequest.description())
                .dateP(new Date())
                .build();

        postRepository.save(post);


        List<Image> images = new ArrayList<>();
        for (ImageRequestDto imageRequest : postRequest.images()) {
            Image newImage = new Image();
            newImage.setPost(post);
            newImage.setImageUrl(imageRequest.imageUrl());
            images.add(newImage);
            System.out.println(newImage);
        }

        if (!images.isEmpty()) {
            imageRepository.saveAll(images);
        }

        return post;
    }

    @Transactional
    public List<Post> findAllByUserId(UUID user_id) {
        List<Post> posts = postRepository.findPostsByUserIdWithImages(user_id);

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("No existen posts del usuario con el id"+user_id);
        }

        return posts;
    }

    @Transactional
    public List<Post> findAllByUsername(String username) {
        List<Post> posts = postRepository.findPostsByUsername(username);

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("No existen posts del usuario "+username);
        }

        return posts;
    }

}
