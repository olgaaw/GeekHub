package com.salesianos.geekhub;

import com.salesianos.geekhub.model.*;
import com.salesianos.geekhub.repository.*;
import com.salesianos.geekhub.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private User user;
    private Post post;
    private UUID userId;
    private UUID postId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        postId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        user.setUsername("testuser");

        post = new Post();
        post.setId(postId);
        post.setUser(user);
        post.setDescription("Test post");
    }

    @Test
    void findAllByUserId_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postPage = new PageImpl<>(List.of(post));
        Mockito.when(postRepository.findPostsByUserIdWithImages(userId, pageable)).thenReturn(postPage);

        Page<Post> result = postService.findAllByUserId(userId, 0, 10);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void findAllByUserId_NotFound() {
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(postRepository.findPostsByUserIdWithImages(userId, pageable)).thenReturn(Page.empty());

        assertThrows(EntityNotFoundException.class, () -> postService.findAllByUserId(userId, 0, 10));
    }

    @Test
    void findAllByUsername_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postPage = new PageImpl<>(List.of(post));
        Mockito.when(postRepository.findPostsByUsername("testuser", pageable)).thenReturn(postPage);

        Page<Post> result = postService.findAllByUsername("testuser", 0, 10);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void deleteByUser_Success() {
        Mockito.when(postRepository.findPostById(postId)).thenReturn(post);

        postService.deleteByUser(postId, user);

        verify(postRepository).delete(post);
    }

    @Test
    void deleteByUser_NotOwner() {
        User anotherUser = new User();
        anotherUser.setId(UUID.randomUUID());
        post.setUser(anotherUser);
        Mockito.when(postRepository.findPostById(postId)).thenReturn(post);

        assertThrows(RuntimeException.class, () -> postService.deleteByUser(postId, user));
    }
}
