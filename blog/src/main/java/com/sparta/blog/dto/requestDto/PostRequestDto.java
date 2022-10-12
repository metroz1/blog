package com.sparta.blog.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
}
