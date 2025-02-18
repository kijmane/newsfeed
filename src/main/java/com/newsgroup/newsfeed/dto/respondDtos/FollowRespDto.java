package com.newsgroup.newsfeed.dto.respondDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowRespDto {
    private String message;

    public FollowRespDto(String followed, String follower) {
        this.message = follower + " 님이 " + followed + " 님을 팔로우 하였습니다.";
    }
}
