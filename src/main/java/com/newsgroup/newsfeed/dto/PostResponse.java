package com.newsgroup.newsfeed.dto;

import com.newsgroup.newsfeed.entity.Posts;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostResponse {
    // 게시물 응답 DTO (게시물 조회 시 클라이언트에게 반환할 데이터 구조 정의)
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