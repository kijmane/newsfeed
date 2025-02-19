package com.newsgroup.newsfeed.dto.respondDtos.follow;

import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FollowRespDto {
    private Long id;

    private Users follower;

    private Users followed;

    private LocalDateTime createdDate;

    private LocalDateTime unfollowDate;

    public FollowRespDto(Follow follow) {
        this.id = follow.getId();
        this.followed = follow.getFollowed();
        this.follower = follow.getFollower();
        this.createdDate = follow.getCreatedDate();
        this.unfollowDate = follow.getUnfollowDate();
    }
}
