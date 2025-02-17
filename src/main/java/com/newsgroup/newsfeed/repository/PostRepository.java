package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
