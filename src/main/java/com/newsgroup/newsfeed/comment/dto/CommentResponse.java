package com.newsgroup.newsfeed.comment.dto;

import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final LocalDateTime createdAt;
    private final boolean canModifyOrDelete; // 본인 댓글이거나 게시글 주인이면 true (수정/삭제 가능)

    public CommentResponse(Comment comment, User user) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.canModifyOrDelete = comment.isOwnerOrPostOwner(user); // 본인 or 게시물 주인 여부 확인


    }
}