package com.newsgroup.newsfeed.service;

import com.newsgroup.newsfeed.dto.CommentResponse;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(Posts post, Users currentUser);
    void updateComment(Users user, Comment comment, String content);
    void deleteComment(Users user, Comment comment);
}