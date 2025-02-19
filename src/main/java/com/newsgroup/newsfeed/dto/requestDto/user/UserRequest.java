package com.newsgroup.newsfeed.dto.requestDto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String nickname;
    private String password;

    public UserRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}