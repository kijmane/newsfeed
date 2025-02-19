package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
/*
    댓글 관련 서비스 구현체
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    /*
        특정 게시물 댓글 목록 페이징 처리하여 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId, Users currentUser, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Comment> commentPage = commentRepository.findByPostId (postId, pageable);
        return commentPage.stream()
                .map(comment -> CommentResponse.from(comment, currentUser))
                .collect(Collectors.toList());
    }
    /*
        댓글 수정 (작성자만 가능)
     */
    @Override
    @Transactional
    public CommentResponse updateComment(Users user, Long commentId, CommentRequest request) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        comment.updateContent(request.getContent());
        return CommentResponse.from(comment, user);
    }
    /*
        댓글 삭제 (작성자만 가능)
     */
    @Override
    @Transactional
    public void deleteComment(Users user, Long commentId) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        commentRepository.delete(comment);
    }

    /*
        댓글 ID로 댓글 조회 (존재하지 않으면 예외 발생)
     */
    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    /*
        댓글 수정 또는 삭제 권한 확인
     */
    private void checkCommentPermission(Comment comment, Users user) {
        if (comment == null) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }
        if (user == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
    }
}