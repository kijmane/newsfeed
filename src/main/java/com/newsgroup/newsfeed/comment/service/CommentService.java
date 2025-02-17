package com.newsgroup.newsfeed.comment.service;

import com.newsgroup.newsfeed.comment.dto.CommentResponse;
import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.comment.repository.CommentRepository;
import com.newsgroup.newsfeed.comment.entity.Post;
import com.newsgroup.newsfeed.comment.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    // updateComment , deleteComment에서 검증
    private final CommentRepository commentRepository;

    // 특정 게시물의 댓글 목록 조회하는 메서드 (게시물 존재 여부 확인 추가)
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Post post, User currentUser) {
        if (post == null) {
            throw new IllegalArgumentException("해당 게시물이 존재하지 않습니다.");
        }
        return commentRepository.findByPost(post)
                .stream()
                .map(comment -> new CommentResponse(comment, currentUser))
                .collect(Collectors.toList());
    }
    // 댓글 수정
    @Transactional
    public void updateComment(User user, Comment comment, String content) {
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        comment.updateContent(content);
    }
    // 댓글 삭제
    @Transactional
    public void deleteComment(User user, Comment comment) {
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }
}