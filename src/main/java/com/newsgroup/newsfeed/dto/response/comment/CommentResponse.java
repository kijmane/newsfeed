package com.newsgroup.newsfeed.dto.response.comment;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String nickname;
    private String content;
    private boolean canModifyOrDelete;
    // 생성자 자체를 외부에 공개하지않으려함!
    public CommentResponse(Comment comment, Users currentUser) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.canModifyOrDelete = Objects.equals(comment.getUser(), currentUser);
    }
}