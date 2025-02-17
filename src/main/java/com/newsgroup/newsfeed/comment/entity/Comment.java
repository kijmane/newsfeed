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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 해당 댓글이 달린 게시물

    @Column(nullable = false)
    private String content; // 댓글 내용

    @Column(nullable = false, updatable = false)  // 처음 생성된 이후 변경 불가능
    private LocalDateTime createdAt; // 댓글 생성 날짜

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 댓글 수정 날짜

    // 새로운 댓글이 생성될 때 자동으로 실행되는 메서드
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 현재 시간으로 생성 시간 설정
        this.updatedAt = LocalDateTime.now(); // 수정 시간도 동일하게 설정
    }

    // 댓글이 수정될 때 자동으로 실행되는 메서드
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now(); // 수정 시점 기록
    }

    // 댓글 내용 수정 메서드
    public void updateContent(String content) {
        this.content = content; // 새로운 댓글 내용으로 변경
        this.updatedAt = LocalDateTime.now(); // 수정 시점 기록
    }

    // 외부에서 수정 시간을 직접 변경할 수 있도록 하는 메서드
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // 본인이 작성한 댓글이거나 해당 게시물의 주인인지 확인하는 메서드
    public boolean isOwnerOrPostOwner(User user) {
        return this.user.equals(user) || // 댓글 작성자인 경우
                (this.post != null && this.post.getUser() != null && this.post.getUser().equals(user)); // 게시물 주인인 경우
    }
}