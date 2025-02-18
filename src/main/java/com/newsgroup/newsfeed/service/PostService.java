package com.newsgroup.newsfeed.service;

import com.newsgroup.newsfeed.dto.PostRequest;
import com.newsgroup.newsfeed.dto.PostResponse;
import com.newsgroup.newsfeed.entity.Users;

import java.util.List;

public interface PostService {
    // 새로운 게시물 생성 메서드
    PostResponse createPost(Users user, PostRequest request);
    // 전체 게시물 조회 메서드
    List<PostResponse> getAllPosts();
}