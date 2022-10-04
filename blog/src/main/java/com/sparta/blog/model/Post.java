package com.sparta.blog.model;

import com.sparta.blog.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;

    public Post(PostDto postDto) {

        title = postDto.getTitle();
        content = postDto.getContent();
        writer = postDto.getWriter();
        password = postDto.getPassword();
    }

    public Post updateDto(PostDto postDto) {

        title = postDto.getTitle();
        content = postDto.getContent();
        writer = postDto.getWriter();
        password = postDto.getPassword();

        return this;
    }
}
