package com.newsgroup.newsfeed.entity.posts;

import com.newsgroup.newsfeed.config.BaseEntity;
import com.newsgroup.newsfeed.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Posts extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String content;
    private Long thumbsUpNum;
    private Long commentsNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    public Posts(String email,
                 String content,
                 Long thumbsUpNum,
                 Long commentsNum,
                 String users
    ) {
        this.email = email;
        this.content = content;
        this.thumbsUpNum = thumbsUpNum;
        this.commentsNum = commentsNum;
    }

    // 좋아요수를 증가시키는 메서드
    public void increaseThumbsUp() {
        this.thumbsUpNum++;
    }

    // 댓글수를 증가시키는 메서드
    public void incrementCommentsNum() {
        this.commentsNum++;
    }
}
