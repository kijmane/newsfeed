package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.requestDtos.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.requestDtos.comment.CommentResponse;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(java.lang.Long postId, Users currentUser) {
        Posts post = getPostById(postId);
        return commentRepository.findByPost(post)
                .stream()
                .map(comment -> new CommentResponse(comment, currentUser))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateComment(Users user, java.lang.Long commentId, CommentRequest request) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        comment.updateContent(request.getContent());
    }

    @Override
    @Transactional
    public void deleteComment(Users user, java.lang.Long commentId) {
        Comment comment = getCommentById(commentId);
        checkCommentPermission(comment, user);
        commentRepository.delete(comment);
    }

    private Posts getPostById(java.lang.Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    private Comment getCommentById(java.lang.Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    private void checkCommentPermission(Comment comment, Users user) {
        if (!comment.isOwnerOrPostOwner(user)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
    }
}