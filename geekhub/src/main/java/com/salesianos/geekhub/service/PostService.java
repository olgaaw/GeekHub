package com.salesianos.geekhub.service;

import com.salesianos.geekhub.dto.post.CreatePostRequestDto;
import com.salesianos.geekhub.error.UserNotFoundException;
import com.salesianos.geekhub.files.exception.StorageException;
import com.salesianos.geekhub.files.model.FileMetadata;
import com.salesianos.geekhub.files.service.StorageService;
import com.salesianos.geekhub.model.*;
import com.salesianos.geekhub.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final StorageService storageService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

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

        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
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
            }
        }

        post.setImages(images);

        return postRepository.save(post);
    }


    @Transactional
    public Page<Post> findAllByUserId(UUID userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findPostsByUserIdWithImages(userId, pageable);

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("No existen posts del usuario con el id " + userId);
        }

        return posts;
    }

    @Transactional()
    public List<Post> findAllByUserId(UUID userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("No existen posts del usuario con el id " + userId);
        }

        return posts;
    }

    @Transactional
    public Page<Post> findAllByUsername(String username, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findPostsByUsername(username, pageable);

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("No existen posts del usuario " + username);
        }

        return posts;
    }


    @Transactional
    public Post findDetailsById(UUID postId) {
        Post post = postRepository.findPostById(postId);

        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        List<Image> images = imageRepository.findImagesByPostId(postId);
        List<Like> likes = likeRepository.findLikesByPostId(postId);

        post.setComments(comments);
        post.setImages(images);
        post.setLikes(likes);

        return post;
    }

    public Page<User> getUsersLikedPost(UUID postId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findUsersLikedPost(postId, pageable);
    }

    @Transactional
    public void deleteByUser(UUID postId, User user) {

        Post post = postRepository.findPostById(postId);

        if (post.getUser().getId().equals(user.getId())) {
            postRepository.delete(post);

        } else {
            throw new RuntimeException("Error al eliminar. El post no pertenece al usuario loggeado");
        }

    }

    @Transactional
    public List<Post> getTimelinePosts(UUID userId) {
        return postRepository.findTimelinePosts(userId);
    }


}
