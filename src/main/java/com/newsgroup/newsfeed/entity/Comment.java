package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
/*
    댓글 엔티티
    - 게시글에 달린 댓글 정보를 저장
    - 댓글 작성자와 게시물 정보 포함
 */
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

    /*
        댓글 내용 수정
     */
    public void updateContent(String content) {
        this.content = content.trim();
    }
    /*
        댓글 작성자 또는 게시글 작성자인지 확인
     */
    public boolean isOwnerOrPostOwner(Users user) {
        return Objects.equals(this.user, user) ||
                (this.post != null && this.post.getUser() != null && Objects.equals(this.post.getUser(), user));
    }
}