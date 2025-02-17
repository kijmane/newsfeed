package com.newsgroup.newsfeed.comment.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @Column(nullable = false, length = 500)
    private String content; // 게시글 내용

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 (게시물과 작성자)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 게시글 작성자
}