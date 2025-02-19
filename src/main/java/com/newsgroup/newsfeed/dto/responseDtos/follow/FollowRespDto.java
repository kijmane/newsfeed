package com.newsgroup.newsfeed.dto.responseDtos.follow;

import com.newsgroup.newsfeed.entity.Follow;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FollowRespDto {
    private Long id;

    private Long followerId;

    private Long followedId;

    private LocalDateTime createdDate;

    private LocalDateTime unfollowDate;

    public FollowRespDto(Follow follow) {
        this.id = follow.getId();
        this.followedId = follow.getFollowed().getId();
        this.followerId = follow.getFollower().getId();
        this.createdDate = follow.getCreatedDate();
        this.unfollowDate = follow.getUnfollowDate();
    }
}
