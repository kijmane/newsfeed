package com.newsgroup.newsfeed.service;

import com.newsgroup.newsfeed.dto.CommentRequest;
import com.newsgroup.newsfeed.dto.CommentResponse;
import com.newsgroup.newsfeed.entity.Users;
import java.util.List;

public interface CommentService {
    // 댓글 서비스 인터페이스 : 댓글 관련 기능 정의
    List<CommentResponse> getComments(Long postId, Users currentUser); // 특정 게시물 댓글 목록 조회
    void updateComment(Users user, Long commentId, CommentRequest request); // 댓글 수정
    void deleteComment(Users user, Long commentId); // 댓글 삭제
}