package com.newsgroup.newsfeed.dto.response.post;

import com.newsgroup.newsfeed.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;
    private String content;
    private Long thumbsUpCount;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Long commentsCount;


    public PostResponse(Long id,
                        String content,
                        Long thumbsUpCount,
                        LocalDateTime createdDate,
                        LocalDateTime updateDate,
                        Long commentsCount) {
        this.id = id;
        this.content = content;
        this.thumbsUpCount = thumbsUpCount;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.commentsCount = commentsCount;
    }

    public PostResponse(Posts post) {
        this.commentsCount = post.getCommentsCount();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.id = post.getId();
        this.thumbsUpCount = post.getThumbsUpCount();
        this.updateDate = post.getUpdateDate();
    }
}
