package com.newsgroup.newsfeed.dto.request.follow;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchMyFollowerRequest {
    private Users targetUser;
}
