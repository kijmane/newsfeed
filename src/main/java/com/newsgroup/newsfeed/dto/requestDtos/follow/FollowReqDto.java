package com.newsgroup.newsfeed.dto.requestDtos.follow;

import com.newsgroup.newsfeed.entity.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowReqDto {

    private Users follower;

    private Users followed;

    public FollowReqDto(Users followed, Users follower) {
        this.followed = followed;
        this.follower = follower;
    }
}
