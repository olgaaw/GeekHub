package com.salesianos.geekhub.cotroller;

import com.salesianos.geekhub.dto.post.PostRequestDto;
import com.salesianos.geekhub.dto.post.PostResponseDto;
import com.salesianos.geekhub.model.Post;
import com.salesianos.geekhub.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> crear(@RequestBody PostRequestDto postRequest) {
        Post post = postService.crearPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostResponseDto.of(post));
    }
}
