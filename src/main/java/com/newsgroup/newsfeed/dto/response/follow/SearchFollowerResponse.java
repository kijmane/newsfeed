<<<<<<<< HEAD:src/main/java/com/newsgroup/newsfeed/dto/responseDto/follow/SearchFollowerResponse.java
package com.newsgroup.newsfeed.dto.responseDto.follow;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchFollowerResponse {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private Long followNum = 0L;
    private Long followingNum = 0L;

    public SearchFollowerResponse(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.followNum = user.getFollowNum();
        this.nickname = user.getNickname();
        this.followingNum = user.getFollowingNum();
        this.password = user.getPassword();
    }
}
========
package com.newsgroup.newsfeed.dto.response.follow;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchFollowerResponse {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private Long followNum = 0L;
    private Long followingNum = 0L;

    public SearchFollowerResponse(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.followNum = user.getFollowNum();
        this.nickname = user.getNickname();
        this.followingNum = user.getFollowingNum();
        this.password = user.getPassword();
    }
}
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/response/follow/SearchFollowerResponse.java
