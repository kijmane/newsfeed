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
     * 특정 게시물의 댓글 목록 조회 (하나의 게시물에 목록 / 페이징 추가!! / 작성순으로 보여줄건지 시간순으로 보여줄건지)
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @RequestHeader Users user,
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        List<CommentResponse> comments = commentService.getComments(postId, user, page, size, sortBy, direction);
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