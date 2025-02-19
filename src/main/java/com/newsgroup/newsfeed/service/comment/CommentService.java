package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.requestDtos.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.requestDtos.comment.CommentResponse;
import com.newsgroup.newsfeed.entity.Users;
import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(java.lang.Long postId, Users currentUser);
    void updateComment(Users user, java.lang.Long commentId, CommentRequest request);
    void deleteComment(Users user, java.lang.Long commentId);
}