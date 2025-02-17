package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // 이메일을 통해 사용자 조회

}
