package com.newsgroup.newsfeed.dto.response.user;

import com.newsgroup.newsfeed.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private Long followNum;

    private Long followingNum;

    public UserResponse(Users user) {
        this.email = user.getEmail();
        this.followingNum = user.getFollowingNum();
        this.followNum = user.getFollowNum();
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
    }
}