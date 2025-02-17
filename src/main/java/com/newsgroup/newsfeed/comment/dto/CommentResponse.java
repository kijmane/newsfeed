package com.newsgroup.newsfeed.comment.dto;

import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.comment.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final LocalDateTime createdAt;
    private final boolean canModifyOrDelete; // 댓글 작성자인지 여부 추가

    public CommentResponse(Comment comment, User currentUser) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.canModifyOrDelete = comment.getUser().equals(currentUser); // 현재 사용자가 댓글 작성자인지 체크
    }
}