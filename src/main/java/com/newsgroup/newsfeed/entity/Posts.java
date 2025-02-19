package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Builder
public class Posts extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long thumbsUpCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    public Posts(String email,
                 String content,
                 Long thumbsUpCount,
                 Users user
    ) {
        this.email = email;
        this.content = content;
        this.thumbsUpCount = thumbsUpCount;
        this.user = user;

    }

    public void update(String content) {
        this.content = content;
    }

    // 좋아요수 증가
    public void increaseThumbsUp() {
        this.thumbsUpCount++; // thumbsUpCount 값을 1 증가
    }

    public void setThumbsUpCount(Long thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
    }

    // 댓글 수를 반환하는 메서드
    public Long getCommentsCount() {
        return commentList != null ? (long) commentList.size() : 0L; // commentList가 null이면 0을 반환
    }

    // 댓글 수 증가 메서드 (수정)
    public void increaseCommentsCount() {
        this.commentList.add(new Comment()); // 댓글 수 증가. 실제 댓글은 새로운 객체로 추가하지 않고 리스트에 추가만 함
        // 실제 댓글 객체 추가를 원하시면, 주어진 게시글에 해당하는 댓글을 추가하는 방식으로 수정 필요
    }
}
