package com.newsgroup.newsfeed.dto.respondDtos.follow;

import com.newsgroup.newsfeed.entity.Users;

import java.util.List;

public class SearchMyFollowerRespDto {
    List<Users> userList;

    public SearchMyFollowerRespDto(List<Users> usersList) {
        this.userList = usersList;
    }
}
