package com.sparta.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private String createdAt;
}
