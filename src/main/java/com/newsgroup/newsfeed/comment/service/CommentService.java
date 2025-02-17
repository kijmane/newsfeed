package com.newsgroup.newsfeed.comment.service;

import com.newsgroup.newsfeed.comment.dto.CommentRequest;
import com.newsgroup.newsfeed.comment.dto.CommentResponse;
import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.comment.repository.CommentRepository;
import com.newsgroup.newsfeed.post.entity.Post;
import com.newsgroup.newsfeed.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    // 게시물 주인 또는 본인 댓글만 수정/삭제 가능
    // updateComment , deleteComment에서 검증
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse addComment(User user, Post post, CommentRequest request) {
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(request.getContent())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        return new CommentResponse(comment, user);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Post post, User user) {
        return commentRepository.findByPost(post)
                .stream()
                .map(comment -> new CommentResponse(comment, user))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateComment(User user, Comment comment, String content) {
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        comment.updateContent(content);
    }

    @Transactional
    public void deleteComment(User user, Comment comment) {
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }
}