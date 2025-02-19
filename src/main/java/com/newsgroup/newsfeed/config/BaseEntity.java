package com.newsgroup.newsfeed.config;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


// 다른 엔티티 전역에 쓰일 필드를 정의하는 클래스
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 활성화
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private  LocalDateTime updateDate;
}