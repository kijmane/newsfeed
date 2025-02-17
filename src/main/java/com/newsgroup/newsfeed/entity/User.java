package com.newsgroup.newsfeed.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;   // 이메일 필수, 중복불가

    @Column(nullable = false)
    private String password;    // 비밀번호 필수

    @Column(nullable = false)
    private String nickname;    // 사용자명 필수

    @CreatedDate
    private LocalDateTime createdAt;    // 생성일 자동 저장

    @LastModifiedDate
    private LocalDateTime updatedAt;    // 수정일 자동 업데이트

}
