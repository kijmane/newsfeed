package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.requestDtos.comment.CommentRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.comment.CommentResponseDto;
import com.newsgroup.newsfeed.entity.Users;
import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getComments(java.lang.Long postId, Users currentUser);
    void updateComment(Users user, java.lang.Long commentId, CommentRequestDto request);
    void deleteComment(Users user, java.lang.Long commentId);
}