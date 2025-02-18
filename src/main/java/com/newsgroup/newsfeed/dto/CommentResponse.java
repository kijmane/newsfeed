package com.newsgroup.newsfeed.dto;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final boolean canModifyOrDelete; // 댓글 작성자인지 여부

    public CommentResponse(Comment comment, Users currentUser) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        // 현재 사용자가 해당 댓글을 작성한 경우에만 수정/삭제 가능
        this.canModifyOrDelete = comment.getUser().equals(currentUser);
    }
}