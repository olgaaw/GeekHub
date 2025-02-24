package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.post.ImageRequestDto;
import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.files.exception.StorageException;
import com.salesianos.geekhub.files.model.FileMetadata;
import com.salesianos.geekhub.files.service.StorageService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final StorageService storageService;

    @Transactional
    public Post crearPost(CreatePostRequestDto postRequest, MultipartFile[] files, User user) {

        User user1 = userRepository.findFirstByUsername(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername()));

        Post post = Post.builder()
                .user(user1)
                .description(postRequest.description())
                .dateP(new Date())
                .build();

        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) {
            FileMetadata fileMetadata = storageService.store(file);

            String imageUrl = fileMetadata.getURL();

            if (imageUrl != null) {
                Image image = new Image();
                image.setImageUrl(imageUrl);
                image.setPost(post);
                images.add(image);
            } else {
                throw new StorageException("Error al generar URL para la imagen");
            }
        }

        post.setImages(images);

        return postRepository.save(post);
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
