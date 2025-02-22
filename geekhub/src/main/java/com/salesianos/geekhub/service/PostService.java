package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.post.ImageRequestDto;
import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.model.Image;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.repository.ImageRepository;
import com.salesianos.geekhub.repository.PostRepository;
import com.salesianos.geekhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public Post crearPost(CreatePostRequestDto postRequest) {

        Post newPost = Post.builder()
                .user(userRepository.findById(postRequest.userId()).orElseThrow())
                .description(postRequest.description())
                .date(new Date())
                .build();

        postRepository.save(newPost);


        List<Image> images = new ArrayList<>();
        for (ImageRequestDto imageRequest : postRequest.images()) {
            Image newImage = new Image();
            newImage.setPost(newPost);
            newImage.setImageUrl(imageRequest.imageUrl());
            images.add(newImage);
        }

        if (!images.isEmpty()) {
            imageRepository.saveAll(images);
        }

        return newPost;
    }

}
