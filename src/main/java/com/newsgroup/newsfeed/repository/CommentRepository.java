package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 기존 findByPostId -> findByPost로 수정
    Page<Comment> findByPostId (Long postId, Pageable pageable);
}