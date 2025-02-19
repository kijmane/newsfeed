package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.comment.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 특정 게시물의 댓글 목록 조회
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @RequestHeader Users user,
            @PathVariable Long postId
    ) {
        List<CommentResponse> comments = commentService.getComments(postId, user);
        return ResponseEntity.ok(comments);
    }

    /**
     * 댓글 수정 (작성자만 가능)
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @RequestHeader Users user,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request
    ) {
        return ResponseEntity.ok(commentService.updateComment(user, commentId, request));
    }

    /**
     * 댓글 삭제 (작성자만 가능)
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @RequestHeader Users user,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(user, commentId);
        return ResponseEntity.noContent().build();
    }
}