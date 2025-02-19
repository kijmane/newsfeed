package com.newsgroup.newsfeed.dto.request.follow;

import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchMyFollowingRequest {
    @NotNull
    private Users targetUser;

    private FollowEnum followEnum;
}
