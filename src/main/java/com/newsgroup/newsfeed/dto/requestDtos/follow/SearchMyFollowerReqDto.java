package com.newsgroup.newsfeed.dto.requestDtos.follow;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchMyFollowerReqDto {
    private Users targetUser;
}
