package com.newsgroup.newsfeed.comment.controller;

import com.newsgroup.newsfeed.comment.dto.CommentRequest;
import com.newsgroup.newsfeed.comment.dto.CommentResponse;
import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.comment.repository.CommentRepository;
import com.newsgroup.newsfeed.comment.service.CommentService;
import com.newsgroup.newsfeed.post.entity.Post;
import com.newsgroup.newsfeed.comment.repository.PostRepository;
import com.newsgroup.newsfeed.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    // 게시물 주인 + 본인 댓글 수정/삭제 가능
    private final CommentService commentService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponse> addComment(
            @AuthenticationPrincipal User user,
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest request
    ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return ResponseEntity.ok(commentService.addComment(user, post, request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @AuthenticationPrincipal User user,
            @PathVariable Long postId
    ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return ResponseEntity.ok(commentService.getComments(post, user));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal User user,
            @PathVariable Long commentId,
            @RequestBody String content
    ) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        commentService.updateComment(user, comment, content);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal User user,
            @PathVariable Long commentId
    ) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        commentService.deleteComment(user, comment);
        return ResponseEntity.ok().build();
    }
}