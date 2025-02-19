package com.newsgroup.newsfeed.dto;

import com.newsgroup.newsfeed.entity.Posts;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    public PostResponse(Posts post) {
        this.id = post.getId();
        this.author = post.getUser().getNickname();
        this.content = post.getContent();
        this.createdAt = post.getCreatedDate();
    }
}