package com.newsgroup.newsfeed.dto.respondDtos.follow;

import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UnFollowRespDto {
    private Long id;

    private Long followerId; // 팔로우 하는 사람

    private Long followedId; //팔로우 당하는 사람

    private LocalDateTime createdDate;

    private LocalDateTime unfollowDate; //언팔로우 날짜 기록용 필드

    public UnFollowRespDto(Follow follow) {
        this.createdDate = follow.getCreatedDate();
        this.followedId = follow.getFollowed().getId();
        this.followerId = follow.getFollower().getId();
        this.id = follow.getId();
        this.unfollowDate = LocalDateTime.now();
    }
}
