package com.newsgroup.newsfeed.dto.response.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;
    private String email;
    private String content;
    private Long thumbsUpCount;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Long commentsCount;

    public PostResponse(Long id,
                        String email,
                        String content,
                        Long thumbsUpCount,
                        LocalDateTime createdDate,
                        LocalDateTime updateDate,
                        Long commentsCount
    ) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.thumbsUpCount = thumbsUpCount;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.commentsCount = commentsCount;
    }
}
