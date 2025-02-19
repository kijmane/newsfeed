<<<<<<<< HEAD:src/main/java/com/newsgroup/newsfeed/dto/requestDto/user/UserProfileRequest.java
package com.newsgroup.newsfeed.dto.requestDto.user;
========
package com.newsgroup.newsfeed.dto.request.user;
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/request/user/UserProfileRequest.java

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileRequest {
    private String email;
    private String newNickname;
    private String password;

    public UserProfileRequest(String email, String newNickname, String password) {
        this.email = email;
        this.newNickname = newNickname;
        this.password = password;
    }
}