package com.newsgroup.newsfeed.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {

    private String email;
    private String content;
    private Long thumbsUpNum;
    private Long commentsNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
