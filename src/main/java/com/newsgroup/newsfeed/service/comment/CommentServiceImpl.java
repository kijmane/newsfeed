package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
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
    private final CommentRepository commentRepository;
    private final PostRepository postRepository; // 게시물 조회를 위한 추가

    // 특정 게시물의 댓글 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId, Users currentUser) {
        Posts post = getPostById(postId);
        return commentRepository.findByPost(post) // postId 대신 post 엔티티로 조회
                .stream()
                .map(comment -> new CommentResponse(comment, currentUser))
                .collect(Collectors.toList());
    }

    // 댓글 수정
    @Override
    @Transactional
    public CommentResponse updateComment(Users user, Long commentId, CommentRequest request) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        comment.updateContent(request.getContent());
        return new CommentResponse(comment, user); // 업데이트된 댓글을 반환하도록 수정
    }

    // 댓글 삭제
    @Override
    @Transactional
    public void deleteComment(Users user, Long commentId) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        commentRepository.delete(comment);
    }

    // 댓글 ID로 댓글 찾기
    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    // 게시물 ID로 게시물 찾기
    private Posts getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    // 댓글 작성자 또는 게시물 소유자인지 확인
    private void checkCommentPermission(Comment comment, Users user) {
        if (!isOwnerOrPostOwner(comment, user)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
    }

    // 댓글 작성자 또는 게시물 작성자인지 확인하는 메서드
    private boolean isOwnerOrPostOwner(Comment comment, Users user) {
        return comment.getUser().equals(user) || comment.getPost().getUser().equals(user);
    }
}