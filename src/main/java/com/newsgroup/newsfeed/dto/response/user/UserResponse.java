<<<<<<<< HEAD:src/main/java/com/newsgroup/newsfeed/dto/responseDto/user/UserResponseDto.java
package com.newsgroup.newsfeed.dto.responseDto.user;
========
package com.newsgroup.newsfeed.dto.response.user;
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/response/user/UserResponse.java

import com.newsgroup.newsfeed.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}