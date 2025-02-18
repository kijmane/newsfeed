package com.newsgroup.newsfeed.dto.requestDtos.posts;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsRequestDto {

    private String email;
    private String content;
    private Long thumbsUpCount;
    private Long commentsCount;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
