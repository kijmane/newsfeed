package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Posts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Long thumbsUpNum;

    @Column(nullable = false)
    private Long commentsNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // 빌더 패턴을 사용할 경우, 기본값 설정을 위한 빌더 메서드 추가
    @Builder
    public Posts(String email, String content, Users user) {
        this.email = email;
        this.content = content;
        this.user = user;
        this.thumbsUpNum = 0L;
        this.commentsNum = 0L;
        this.comments = new ArrayList<>();
    }
}