package com.newsgroup.newsfeed.service.comment;

import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.request.comment.CommentSaveReqDto;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
import com.newsgroup.newsfeed.dto.response.comment.CommentSaveRespDto;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import java.util.List;
/*
    댓글 관련 서비스 인터페이스
    - 댓글 조회 , 수정 , 삭제 제공
    - 특정 게시물 댓글 목록 페이징 처리
 */
public interface CommentService {
    CommentSaveRespDto saveComment(Comment comment);

    List<CommentResponse> getComments(Long postId, Users currentUser, int page, int size, String sortBy, String direction);
    CommentResponse updateComment(Users user, Long commentId, CommentRequest request);
    void deleteComment(Users user, Long commentId);
}