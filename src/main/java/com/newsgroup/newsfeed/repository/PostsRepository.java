package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
