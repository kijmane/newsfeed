package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Posts post);
}