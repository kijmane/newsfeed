package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.CommentRequest;
import com.newsgroup.newsfeed.dto.CommentResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @AuthenticationPrincipal Users user,
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(commentService.getComments(postId, user));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal Users user,
            @PathVariable Long commentId,
            @RequestBody CommentRequest request
    ) {
        commentService.updateComment(user, commentId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal Users user,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(user, commentId);
        return ResponseEntity.ok().build();
    }
}