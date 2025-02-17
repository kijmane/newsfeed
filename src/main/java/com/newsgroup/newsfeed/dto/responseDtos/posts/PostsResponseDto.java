package com.newsgroup.newsfeed.dto.responseDtos.posts;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {

    private Long id;
    private String email;
    private String content;
    private Long thumbsUpNum;
    private Long commentsNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostsResponseDto(Long id,
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
