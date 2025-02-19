package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.comment.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.newsgroup.newsfeed.config.GetLoginUser.getLoginUser;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 특정 게시물의 댓글 목록 조회 (세션 인증 구현 후 수정 예정)
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
<<<<<<< HEAD
            @RequestHeader Users user, // 세션 통해 사용자 정보 가져올 예정
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        // 세션 인증 구현 후 추가 될 예정
        List<CommentResponse> comments = commentService.getComments(postId, user, page, size, sortBy, direction);
=======
            HttpSession session,
            @PathVariable Long postId
    ) {
        Users user = getLoginUser(session);
        List<CommentResponse> comments = commentService.getComments(postId, user);
>>>>>>> dev
        return ResponseEntity.ok(comments);
    }

    /**
     * 댓글 수정 (작성자만 가능)
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
<<<<<<< HEAD
            @RequestHeader Users user,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request
    ) {
        CommentResponse updatedComment = commentService.updateComment(user, commentId, request);
        return ResponseEntity.ok(updatedComment);
=======
            HttpSession session,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request
    ) {
        Users user = getLoginUser(session);
        return ResponseEntity.ok(commentService.updateComment(user, commentId, request));
>>>>>>> dev
    }

    /**
     * 댓글 삭제 (작성자만 가능)
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
<<<<<<< HEAD
            @RequestHeader Users user,
=======
            HttpSession session,
>>>>>>> dev
            @PathVariable Long commentId
    ) {
        Users user = getLoginUser(session);
        commentService.deleteComment(user, commentId);
        return ResponseEntity.noContent().build();
    }
}