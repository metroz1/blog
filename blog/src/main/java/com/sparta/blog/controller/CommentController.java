package com.sparta.blog.controller;

import com.sparta.blog.dto.requestDto.CommentRequestDto;
import com.sparta.blog.dto.responseDto.CommentResponseDto;
import com.sparta.blog.model.Member;
import com.sparta.blog.service.CommentService;
import com.sparta.blog.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/auth/comment")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.createComment(commentRequestDto, userDetails.getMember());
    }

    @GetMapping("/api/comment/{id}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long id) {
        return commentService.getComments(id);
    }

    @PutMapping("/api/auth/comment/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return commentService.updateComment(id, commentRequestDto, member);
    }
    @DeleteMapping("/api/auth/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return new ResponseEntity<>(commentService.deleteComment(id, member), HttpStatus.OK);
    }
}
