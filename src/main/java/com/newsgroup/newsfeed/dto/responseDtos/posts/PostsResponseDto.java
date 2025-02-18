package com.newsgroup.newsfeed.dto.responseDtos.posts;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {

    private Long id;
    private String email;
    private String content;
    private Long thumbsUpCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long commentsCount;

    public PostsResponseDto(Long id,
                            String email,
                            String content,
                            Long thumbsUpCount,
                            LocalDateTime createdAt,
                            LocalDateTime modifiedAt,
                            Long commentsCount
    ) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.thumbsUpCount = thumbsUpCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentsCount = commentsCount;
    }
}
