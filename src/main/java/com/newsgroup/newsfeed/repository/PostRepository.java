package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findById(Long id);
}