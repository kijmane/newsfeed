package com.newsgroup.newsfeed.comment.entity;

import com.newsgroup.newsfeed.post.entity.Post;
import com.newsgroup.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {
    // 본인이 작성한 댓글이거나 해당 게시물의 주인이면 수정/삭제 가능
    // isOwnerOrPostOwner(User user) 메서드로 검증
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;  // 해당 댓글이 달린 게시물

    @Column(nullable = false)
    private String content; // 댓글 내용

    private LocalDateTime createdAt; // 댓글 작성 날짜
    private LocalDateTime updatedAt; // 댓글 수정 날짜

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now(); // 수정 시점 기록
    }

    public boolean isOwnerOrPostOwner(User user) {
        // 댓글 작성자 OR 게시물 작성자만 수정/삭제 가능하도록 체크하는 메서드
        return this.user.equals(user) || this.post.getUser().equals(user);
    }
}