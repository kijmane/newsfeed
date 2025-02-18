package com.newsgroup.newsfeed.dto.requestDtos.follow;


import com.newsgroup.newsfeed.entity.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowedDto {

    @NotNull
    private Users follower;

    @NotNull
    private Users followed;
}
