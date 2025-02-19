package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Comment(Users user, Posts post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content.trim();
    }

    public boolean isOwnerOrPostOwner(Users user) {
        return Objects.equals(this.user, user) ||
                (this.post != null && this.post.getUser() != null && Objects.equals(this.post.getUser(), user));
    }
}