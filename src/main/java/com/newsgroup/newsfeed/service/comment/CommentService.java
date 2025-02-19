package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
import com.newsgroup.newsfeed.entity.Users;
import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(Long postId, Users currentUser, int page, int size, String sortBy, String direction);
    CommentResponse updateComment(Users user, Long commentId, CommentRequest request);
    void deleteComment(Users user, Long commentId);
}