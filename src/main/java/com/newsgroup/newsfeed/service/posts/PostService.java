package com.newsgroup.newsfeed.service.posts;

import com.newsgroup.newsfeed.dto.requestDtos.post.PostRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.post.PostResponseDto;
import com.newsgroup.newsfeed.entity.Users;

import java.util.List;

public interface PostService {
    PostResponseDto createPost(Users user, PostRequestDto request);
    List<PostResponseDto> findAll(int page, int size);
    PostResponseDto update(Long id, String email, PostRequestDto dto);
    void deleteById(Long id, String email);
    void increaseThumbsUp(Long postId);
    void addComment(Long postId);
}

