package com.sparta.blog.service;

import com.sparta.blog.dto.requestDto.CommentRequestDto;
import com.sparta.blog.dto.responseDto.CommentResponseDto;
import com.sparta.blog.model.Comment;
import com.sparta.blog.model.Member;
import com.sparta.blog.repository.CommentRepository;
import com.sparta.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(CommentRequestDto commentRequestDto, Member member) {
        Comment comment = commentRepository.save(new Comment().builder()
                .content(commentRequestDto.getContent())
                .post(postRepository.findById(commentRequestDto.getPostid())
                        .orElseThrow(() -> new RuntimeException("댓글 아이디를 찾을 수 없습니다.")))
                        .author(member.getNickname())
                        .member(member)
                .build());
        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<List<CommentResponseDto>> getComments(Long id) {
        List<Comment> commentList = commentRepository.findAllByPost_Id(id);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long id, CommentRequestDto commentRequestDto, Member member) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        if (!comment.getAuthor().equals(member.getNickname()))
            throw new RuntimeException("작성자만 수정/삭제 할 수 있습니다.");

        comment.updateContent(commentRequestDto);
        return new ResponseEntity<>(new CommentResponseDto(comment), HttpStatus.OK);
    }

    @Transactional
    public String deleteComment(Long id, Member member) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 댓글이 없습니다."));
        if(!comment.getAuthor().equals(member.getNickname())) {
            throw new RuntimeException("작성자만 수정/삭제 할 수 있습니다.");
        }
        commentRepository.deleteById(id);
        return "delete success!!!";
    }
}
