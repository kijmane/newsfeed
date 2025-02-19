<<<<<<<< HEAD:src/main/java/com/newsgroup/newsfeed/dto/requestDto/user/UserRequest.java
package com.newsgroup.newsfeed.dto.requestDto.user;
========
package com.newsgroup.newsfeed.dto.request.user;
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/request/user/UserRequest.java

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