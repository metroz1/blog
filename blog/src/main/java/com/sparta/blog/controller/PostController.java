package com.sparta.blog.controller;

import com.sparta.blog.dto.PostDto;
import com.sparta.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/list")
    public List<PostDto> getPosts()  {
        List<PostDto> postDtos = postService.getPosts();
        return postDtos;
    }

    @PostMapping("/post/insert")
    public RedirectView createPost(@ModelAttribute PostDto postDto) {
         postService.createPost(postDto);
        return new RedirectView("/");
    }


    @GetMapping("/post/detail/{id}")
    public PostDto getPostDetail(@PathVariable Long id) {
        PostDto postDto = postService.getPost(id);
        return postDto;
    }

    @PostMapping("/post/detail/delete")
    public String deletePost(@RequestBody PostDto postDto) {
        return postService.deletePost(postDto);
    }

    @GetMapping("/post/modify/{id}")
    public PostDto getPostModify(@PathVariable Long id) {
        PostDto postDto = postService.getPost(id);
        return postDto;
    }

    @PutMapping("/post/detail/update")
    public String updatePost(@RequestBody PostDto postDto) {
        return postService.updatePost(postDto);
    }

    @PostMapping("/post/modify/move") // 수정 버튼 클릭 했을 때
    public String getModifyForm(@RequestBody PostDto postDto) {
        if (postService.passwordCheck(postDto)) {
            String url = "http://localhost:8080/post/modify?id=" + postDto.getId();
            System.out.println(url);
            return url;
        }
        else return "비밀번호 확인";
    }

}
