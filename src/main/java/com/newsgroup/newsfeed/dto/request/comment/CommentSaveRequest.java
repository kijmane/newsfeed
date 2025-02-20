package com.newsgroup.newsfeed.dto.request.comment;


import com.newsgroup.newsfeed.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentSaveRequest {

    private Long postId;

    private String content;

    public CommentSaveRequest(Comment comment) {
        this.content = comment.getContent();
        if (comment.getPost() == null) {
            this.postId = null;
        } else {
            this.postId = comment.getPost().getId();
        }
    }
}
