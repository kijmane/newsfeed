package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowDto;
import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowedDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Follow {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private Users follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id", nullable = false)
    private Users followed;

    @CreatedDate
    private LocalDateTime createdDate;
    private LocalDateTime unfollowDate; //언팔로우 날짜 기록용 필드

    public Follow(FollowDto dto) {
        this.followed = dto.getFollowed();
        this.follower = dto.getFollower();
    }

    public Follow(FollowedDto dto) {
        this.followed = dto.getFollowed();
        this.follower = dto.getFollower();
    }
}
