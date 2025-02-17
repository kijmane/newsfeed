package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Posts;
import com.newsgroup.newsfeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    // 게시물 ID로 게시물 찾기
    Optional<Posts> findById(Long postId);

    // 특정 사용자가 작성한 게시물 찾기
    Optional<Posts> findByUser(Users user);

    // 게시물 존재 여부 확인
    boolean existsById(Long postId);
}