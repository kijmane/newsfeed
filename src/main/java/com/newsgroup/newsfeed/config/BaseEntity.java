package com.newsgroup.newsfeed.config;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * 다른 엔티티 전역에 쓰일 필드를 정의하는 클래스
 */
public abstract class BaseEntity{

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}