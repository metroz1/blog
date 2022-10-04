package com.sparta.blog.service;

import com.sparta.blog.dto.PostDto;
import com.sparta.blog.model.Post;
import com.sparta.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<PostDto> postDtos = new ArrayList<>();
        for(int i = 0; i < posts.size(); i++) {
            postDtos.add(new PostDto());
            postDtos.get(i).setId(posts.get(i).getId());
            postDtos.get(i).setPassword(posts.get(i).getPassword());
            postDtos.get(i).setContent(posts.get(i).getContent());
            postDtos.get(i).setWriter(posts.get(i).getContent());
            postDtos.get(i).setTitle(posts.get(i).getTitle());

            postDtos.get(i).setCreatedAt((posts.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
        }
        return postDtos;
    }
    public String deletePost(PostDto postDto) {
        if (passwordCheck(postDto)) {
            postRepository.deleteById(postDto.getId());
            return "삭제 완료";
        }
        else
            return "비밀번호를 확인해주세요.";
    }

    public Boolean passwordCheck(PostDto postDto) {
        return postRepository.findById(postDto.getId()).orElse(null).getPassword().equals(postDto.getPassword());
    }

    public Post createPost(PostDto postDto) {
        Post post = new Post(postDto);
        postRepository.save(post);
        return post;
    }

    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPassword(post.getPassword());
        postDto.setContent(post.getContent());
        postDto.setWriter(post.getContent());
        postDto.setTitle(post.getTitle());

        postDto.setCreatedAt((post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
        return postDto;
    }

    public String updatePost(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        post.updateDto(postDto);
        postRepository.save(post);
        return "수정 성공";
    }

}
