package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId (Long postId, Pageable pageable);
}