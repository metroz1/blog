package com.sparta.blog.controller;

import com.sparta.blog.dto.requestDto.PostRequestDto;
import com.sparta.blog.dto.responseDto.PostResponseDto;
import com.sparta.blog.service.PostService;
import com.sparta.blog.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/post")
    public ResponseEntity<List<PostResponseDto>> getPosts()  {
        return postService.getPosts();
    }

    @PostMapping("/api/auth/post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.createPost(postRequestDto, userDetails.getMember());
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @DeleteMapping("/api/auth/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getMember());
    }

    @PutMapping("/api/auth/post/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);
    }

}
