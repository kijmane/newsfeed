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

    // 특정 게시물의 댓글 목록 조회 - 본인이 작성한 댓글 , 본인의 게시물에 달린 댓글만 조회 가능
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @AuthenticationPrincipal Users user,
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(commentService.getComments(postId, user));
    }

    // 댓글 수정 - 댓글 작성자 , 게시물 소유자만 수정 가능
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal Users user,
            @PathVariable Long commentId,
            @RequestBody CommentRequest request
    ) {
        commentService.updateComment(user, commentId, request);
        return ResponseEntity.ok().build();
    }
    // 댓글 삭제 - 댓글 작성자 , 게시물 소유자만 삭제 가능
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal Users user,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(user, commentId);
        return ResponseEntity.ok().build();
    }
}