package com.sparta.blog.model;

import com.sparta.blog.dto.requestDto.CommentRequestDto;
import com.sparta.blog.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Member member;

    public Comment(CommentRequestDto requestDto , Member member , Post post ){
        this.content = requestDto.getContent();
        this.author = member.getNickname();
        this.post = post;
        this.member = member;
    }
    public void updateContent(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
