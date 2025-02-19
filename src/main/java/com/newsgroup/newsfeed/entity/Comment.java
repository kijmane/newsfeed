package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts post;

    @Column(nullable = false)
    private String content;

    // 댓글 내용 업데이트
    public void updateContent(String content) {
        this.content = content;
    }

    // 댓글 소유자 혹은 게시물 소유자 확인
    public boolean isOwnerOrPostOwner(Users user) {
        return this.user.equals(user) || (this.post != null && this.post.getUser() != null && this.post.getUser().equals(user));
    }
}