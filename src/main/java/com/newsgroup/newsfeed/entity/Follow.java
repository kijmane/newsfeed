package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.dto.request.follow.FollowRequest;
import com.newsgroup.newsfeed.dto.request.follow.FollowedRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * 유저의 모든 팔로우 관계 정의
 */
@Entity
@Getter
@NoArgsConstructor
public class Follow {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private Users follower; // 팔로우 하는 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id", nullable = false)
    private Users followed; //팔로우 당하는 사람

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime unfollowDate; //언팔로우 날짜 기록용 필드

    public Follow(FollowRequest dto) {
        this.followed = dto.getFollowed();
        this.follower = dto.getFollower();
    }

    public Follow(FollowedRequest dto) {
        this.followed = dto.getFollowed();
        this.follower = dto.getFollower();
    }
}
