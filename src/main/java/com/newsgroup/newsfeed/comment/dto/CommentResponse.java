package com.newsgroup.newsfeed.comment.dto;

import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    // 본인 댓글이거나 본인이 게시물 주인이면 canModifyOrDelete = true
    // 프론트에서 수정/삭제 버튼 표시 가능
    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private boolean canModifyOrDelete;

    public CommentResponse(Comment comment, User user) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.canModifyOrDelete = comment.isOwnerOrPostOwner(user);
    }
}