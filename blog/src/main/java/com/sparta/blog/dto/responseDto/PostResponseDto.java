package com.sparta.blog.dto.responseDto;

import com.sparta.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    private List<CommentResponseDto> commentResponseDtoList;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public void updateCommentDtoList(List<CommentResponseDto> commentResponseDtoList) {
        this.commentResponseDtoList = commentResponseDtoList;
    }

    public PostResponseDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        author = post.getAuthor();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }

}
