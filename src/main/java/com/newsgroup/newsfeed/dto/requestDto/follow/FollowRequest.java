package com.newsgroup.newsfeed.dto.requestDto.follow;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowRequest {

    private Users follower;

    private Users followed;

    public FollowRequest(Users followed, Users follower) {
        this.followed = followed;
        this.follower = follower;
    }
}
