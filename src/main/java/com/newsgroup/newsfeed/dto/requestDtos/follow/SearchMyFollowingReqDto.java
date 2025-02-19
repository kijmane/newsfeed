package com.newsgroup.newsfeed.dto.requestDtos.follow;

import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchMyFollowingReqDto {
    @NotNull
    private Users targetUser;

    private FollowEnum followEnum;
}
