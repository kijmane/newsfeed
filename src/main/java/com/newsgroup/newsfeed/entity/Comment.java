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
    private Users user; // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts post; // 해당 댓글이 달린 게시물

    @Column(nullable = false)
    private String content; // 댓글 내용

   public void updateContent(String content) {
       this.content = content; // 댓글 내용 수정
   }

   public boolean isOwnerOrPostOwner(Users user) {
       // 댓글을 수정/삭제 할 수 있는 권한이 있는지 확인
       return this.user.equals(user) || this.post != null && this.post.getUser() != null && this.post.getUser().equals(user);
   }
}