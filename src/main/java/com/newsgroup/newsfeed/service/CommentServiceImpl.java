package com.newsgroup.newsfeed.service;

import com.newsgroup.newsfeed.dto.CommentRequest;
import com.newsgroup.newsfeed.dto.CommentResponse;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.CommentRepository;
import com.newsgroup.newsfeed.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository; // 댓글 저장소 (JPA 사용)
    private final PostRepository postRepository; // 게시물 저장소

    // 특정 게시물 댓글 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId, Users currentUser) {
        Posts post = getPostById(postId);
        return commentRepository.findByPost(post)
                .stream()
                .map(comment -> new CommentResponse(comment, currentUser))
                .collect(Collectors.toList());
    }
    // 댓글 수정 기능
    @Override
    @Transactional
    public void updateComment(Users user, Long commentId, CommentRequest request) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        comment.updateContent(request.getContent());
    }
    // 댓글 삭제 기능
    @Override
    @Transactional
    public void deleteComment(Users user, Long commentId) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        commentRepository.delete(comment);
    }
    // 게시물 ID를 기반으로 게시물 조회 : 해당 ID 게시물 존재하지 않을 시 예외 발생
    private Posts getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }
    // 댓글 ID를 기반으로 댓글 조회 : 해당 ID 댓글 존재하지 않을 시 예외 발생
    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }
    // 댓글 수정/삭제 권한 확인 : 댓글 작성자 , 댓글 달린 게시물 작성자만 가능
    private void checkCommentPermission(Comment comment, Users user) {
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
    }
}