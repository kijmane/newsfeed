package com.newsgroup.newsfeed.dto.response.comment;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentSaveRespDto {
    private Long id;

    private Users user;

    private Posts post;

    private String content;

    public CommentSaveRespDto(Comment comment) {
        this.content = comment.getContent();
        this.id = comment.getId();
        this.post = comment.getPost();
        this.user = comment.getUser();
    }
}
