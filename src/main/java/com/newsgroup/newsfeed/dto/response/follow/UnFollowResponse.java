<<<<<<<< HEAD:src/main/java/com/newsgroup/newsfeed/dto/responseDto/follow/UnFollowResponse.java
package com.newsgroup.newsfeed.dto.responseDto.follow;

import com.newsgroup.newsfeed.entity.Follow;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UnFollowResponse {
    private Long id;

    private Long followerId; // 팔로우 하는 사람

    private Long followedId; //팔로우 당하는 사람

    private LocalDateTime createdDate;

    private LocalDateTime unfollowDate; //언팔로우 날짜 기록용 필드

    public UnFollowResponse(Follow follow) {
        this.createdDate = follow.getCreatedDate();
        this.followedId = follow.getFollowed().getId();
        this.followerId = follow.getFollower().getId();
        this.id = follow.getId();
        this.unfollowDate = LocalDateTime.now();
    }
}
========
package com.newsgroup.newsfeed.dto.response.follow;

import com.newsgroup.newsfeed.entity.Follow;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UnFollowResponse {
    private Long id;

    private Long followerId; // 팔로우 하는 사람

    private Long followedId; //팔로우 당하는 사람

    private LocalDateTime createdDate;

    private LocalDateTime unfollowDate; //언팔로우 날짜 기록용 필드

    public UnFollowResponse(Follow follow) {
        this.createdDate = follow.getCreatedDate();
        this.followedId = follow.getFollowed().getId();
        this.followerId = follow.getFollower().getId();
        this.id = follow.getId();
        this.unfollowDate = LocalDateTime.now();
    }
}
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/response/follow/UnFollowResponse.java
