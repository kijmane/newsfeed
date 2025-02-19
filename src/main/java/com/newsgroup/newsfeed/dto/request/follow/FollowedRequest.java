package com.newsgroup.newsfeed.dto.requestDto.follow;


import com.newsgroup.newsfeed.entity.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowedRequest {

    @NotNull
    private Users follower;

    @NotNull
    private Users followed;
}
========
package com.newsgroup.newsfeed.dto.request.follow;


import com.newsgroup.newsfeed.entity.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowedRequest {

    @NotNull
    private Users follower;

    @NotNull
    private Users followed;
}
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/request/follow/FollowedRequest.java
