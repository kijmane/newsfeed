package com.newsgroup.newsfeed.comment.repository;

import com.newsgroup.newsfeed.comment.entity.Post;
import com.newsgroup.newsfeed.comment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 게시물 ID로 게시물 찾기
    Optional<Post> findById(Long postId);

    // 특정 사용자가 작성한 게시물 찾기
    Optional<Post> findByUser(User user);

    // 게시물 존재 여부 확인
    boolean existsById(Long postId);
}