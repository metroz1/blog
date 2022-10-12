package com.sparta.blog.service;

import com.sparta.blog.dto.requestDto.PostRequestDto;
import com.sparta.blog.dto.responseDto.CommentResponseDto;
import com.sparta.blog.dto.responseDto.MemberResponseDto;
import com.sparta.blog.dto.responseDto.PostResponseDto;
import com.sparta.blog.model.Comment;
import com.sparta.blog.model.Member;
import com.sparta.blog.model.Post;
import com.sparta.blog.repository.CommentRepository;
import com.sparta.blog.repository.MemberRepository;
import com.sparta.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity<PostResponseDto> createPost(PostRequestDto postRequestDto, Member member) {
        System.out.println(postRequestDto.getTitle() + " " + postRequestDto.getContent());

        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .author(member.getNickname())
                .member(member)
                .build();

        PostResponseDto postResponseDto = new PostResponseDto(postRepository.save(post));
        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<List<PostResponseDto>> getPosts() {

        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (posts.isEmpty()) {
            throw new RuntimeException("불러올 게시글 목록이 없습니다.");
        }
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for(int i = 0; i < posts.size(); i++) {
            postResponseDtoList.add(new PostResponseDto(posts.get(i)));
        }
        return new ResponseEntity<>(postResponseDtoList, HttpStatus.OK);
    }
    public ResponseEntity<String> deletePost(Long id, Member member) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));

        if(!post.getMember().getNickname().equals(member.getNickname()))
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");

        postRepository.deleteById(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }



    public ResponseEntity<PostResponseDto> getPost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물 정보가 없습니다."));

        PostResponseDto postResponseDto = new PostResponseDto(post);

        List<Comment> comments = commentRepository.findAllByPost_Id(id);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for(Comment comment : comments) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        postResponseDto.updateCommentDtoList(commentResponseDtoList);

        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<PostResponseDto> updatePost(Long id, PostRequestDto postRequestDto) {

        postRepository.existsPostById(id);

        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        post.update(postRequestDto);


        PostResponseDto postResponseDto = new PostResponseDto(postRepository.save(post));

        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

}
