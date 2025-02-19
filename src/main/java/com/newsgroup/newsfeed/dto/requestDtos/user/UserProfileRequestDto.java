package com.newsgroup.newsfeed.dto.requestDtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileRequestDto {
    private String email;
    private String newNickname;
    private String password;

    public UserProfileRequestDto(String email, String newNickname, String password) {
        this.email = email;
        this.newNickname = newNickname;
        this.password = password;
    }
}