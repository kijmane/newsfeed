package com.newsgroup.newsfeed.service.posts;

import com.newsgroup.newsfeed.dto.requestDto.post.PostRequest;
import com.newsgroup.newsfeed.dto.responseDto.post.PostResponseDto;
import com.newsgroup.newsfeed.entity.Users;

import java.util.List;

public interface PostService {
    PostResponseDto createPost(Users user, PostRequest request);
    List<PostResponseDto> findAll(int page, int size);
    PostResponseDto update(Long id, String email, PostRequest dto);
    void deleteById(Long id, String email);
    void increaseThumbsUp(Long postId);
    void addComment(Long postId);
}

