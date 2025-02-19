package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findById(Long postId);
    Optional<Posts> findByUser(Users user);
    boolean existsById(Long postId);
}