package com.newsgroup.newsfeed.entity.posts;

import com.newsgroup.newsfeed.config.BaseEntity;
import com.newsgroup.newsfeed.entity.PostComments;
import com.newsgroup.newsfeed.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Posts extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String content;
    private Long thumbsUpCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComments> comments = new ArrayList<>();

    public Posts(String email,
                 String content,
                 Long thumbsUpCount,
                 Long commentsCount,
                 Users users
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
        return (long) comments.size();
    }

}
