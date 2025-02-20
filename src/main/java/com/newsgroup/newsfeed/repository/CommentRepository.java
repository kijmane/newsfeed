package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/*
    댓글 저장소 인터페이스
    - 댓글 데이터를 데이터베이스에서 조회 , 저장 , 삭제 제공
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostId(Long postId, Pageable pageable);
}