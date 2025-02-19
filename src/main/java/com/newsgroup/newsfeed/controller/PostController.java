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

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal Users user,
            @RequestBody PostRequest request
    ) {
        PostResponse response = postService.createPost(user, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}