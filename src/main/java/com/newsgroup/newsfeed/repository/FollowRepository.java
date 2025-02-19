package com.newsgroup.newsfeed.repository;

import com.newsgroup.newsfeed.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
