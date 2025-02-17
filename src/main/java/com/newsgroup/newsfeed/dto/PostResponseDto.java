package com.newsgroup.newsfeed.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id;
    private String email;
    private String content;
    private Long thumbsUpNum;
    private Long commentsNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponseDto(Long id,
                           String email,
                           String content,
                           Long thumbsUpNum,
                           Long commentsNum,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt
    ) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.thumbsUpNum = thumbsUpNum;
        this.commentsNum = commentsNum;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
