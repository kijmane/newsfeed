package com.newsgroup.newsfeed.post.entity;

import com.newsgroup.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @Column(nullable = false, length = 500)
    private String content; // 게시글 내용

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 (게시물과 작성자)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 게시글 작성자

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 게시글 생성 시간

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 게시글 수정 시간

    // 게시글이 생성될 때 자동으로 생성 시간 , 수정 시간 설정
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 게시글이 수정될 때 자동으로 수정 시간 업데이트
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}