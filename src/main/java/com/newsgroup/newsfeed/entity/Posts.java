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

    // 좋아요수 증가 메서드
    public void increaseThumbsUp() {
        this.thumbsUpCount++;
    }

    // 댓글 수를 반환하는 메서드
    public Long getCommentsCount() {
        return commentList != null ? (long) commentList.size() : 0L; // commentList가 null이면 0을 반환
    }

}
