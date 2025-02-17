package com.newsgroup.newsfeed.comment.repository;

import com.newsgroup.newsfeed.comment.entity.Comment;
import com.newsgroup.newsfeed.comment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시물의 댓글 조회 기능 제공
    List<Comment> findByPost(Post post);
}