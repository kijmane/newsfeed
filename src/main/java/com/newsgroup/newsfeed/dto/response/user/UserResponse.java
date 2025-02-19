package com.newsgroup.newsfeed.dto.response.user;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Getter;

@Getter
public class UserResponse {
    private java.lang.Long id;
    private String email;
    private String nickname;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}