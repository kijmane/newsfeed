package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.PostRequest;
import com.newsgroup.newsfeed.dto.PostResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 추가 (인증된 사용자만 가능)
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal Users user,
            @RequestBody PostRequest request
    ) {
        PostResponse response = postService.createPost(user, request);
        return ResponseEntity.ok(response);
    }

    // 전체 게시물 조회 (모든 게시물 조회해서 리스트 형태로 반환)
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}