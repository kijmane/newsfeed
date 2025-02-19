package com.newsgroup.newsfeed.dto.respondDtos.follow;

import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.entity.Users;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SearchFollowerRespDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private Long followNum = 0L;
    private Long followingNum = 0L;

    public SearchFollowerRespDto(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.followNum = user.getFollowNum();
        this.nickname = user.getNickname();
        this.followingNum = user.getFollowingNum();
        this.password = user.getPassword();
    }
}
