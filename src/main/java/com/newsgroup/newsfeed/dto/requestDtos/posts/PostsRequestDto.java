package com.newsgroup.newsfeed.dto.requestDtos.posts;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsRequestDto {

    private String email;
    private String content;
    private Long thumbsUpNum;
    private Long commentsNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
