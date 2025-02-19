package com.newsgroup.newsfeed.dto.responseDto.comment;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final boolean canModifyOrDelete;

    public CommentResponse(Comment comment, Users currentUser) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.canModifyOrDelete = comment.getUser().equals(currentUser);
    }
}