package com.newsgroup.newsfeed.service.posts;

import com.newsgroup.newsfeed.dto.request.post.PostRequest;
import com.newsgroup.newsfeed.dto.response.post.PostResponse;
import com.newsgroup.newsfeed.entity.Users;

import java.util.List;

public interface PostService {
    PostResponse createPost(Users user, PostRequest request);
    List<PostResponse> findAll(int page, int size);
    PostResponse update(Long id, String email, PostRequest dto);
    void deleteById(Long id, String email);
    void increaseThumbsUp(Long postId);
//    void addComment(Long postId);
}

