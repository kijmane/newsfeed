package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.CommentResponse;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.repository.CommentRepository;
import com.newsgroup.newsfeed.service.CommentServiceImpl;
import com.newsgroup.newsfeed.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 특정 게시물 댓글 목록 조회 ( 본인이 작성한 댓글이거나 본인의 게시물에 달린 댓글만 관리 가능 )
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            @AuthenticationPrincipal Users user,
            @PathVariable Long postId
    ) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return ResponseEntity.ok(commentService.getComments(post, user));
    }

    // 댓글 수정 ( 본인 또는 게시물 주인만 가능 )
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal Users user,
            @PathVariable Long commentId,
            @RequestBody String content
    ) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        commentService.updateComment(user, comment, content);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제 ( 본인 또는 게시물 주인만 가능 )
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal Users user,
            @PathVariable Long commentId
    ) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        commentService.deleteComment(user, comment);
        return ResponseEntity.ok().build();
    }
}