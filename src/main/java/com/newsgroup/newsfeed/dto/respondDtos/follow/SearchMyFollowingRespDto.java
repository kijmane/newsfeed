package com.newsgroup.newsfeed.dto.respondDtos.follow;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchMyFollowingRespDto {
    List<Users> userList;

    public SearchMyFollowingRespDto(List<Users> userList) {
        this.userList = userList;
    }
}
