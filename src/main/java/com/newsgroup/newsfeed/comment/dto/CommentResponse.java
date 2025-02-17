package com.newsgroup.newsfeed.comment.dto;

import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.comment.entity.User;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final boolean canModifyOrDelete; // 댓글 작성자인지 여부

    public CommentResponse(Comment comment, User currentUser) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        // 현재 사용자가 해당 댓글을 작성한 경우에만 수정/삭제 가능
        this.canModifyOrDelete = comment.getUser().equals(currentUser);
    }
}