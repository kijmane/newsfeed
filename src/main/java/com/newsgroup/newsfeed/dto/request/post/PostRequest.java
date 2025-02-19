<<<<<<<< HEAD:src/main/java/com/newsgroup/newsfeed/dto/requestDto/post/PostRequest.java
package com.newsgroup.newsfeed.dto.requestDto.post;
========
package com.newsgroup.newsfeed.dto.request.post;
>>>>>>>> dev:src/main/java/com/newsgroup/newsfeed/dto/request/post/PostRequest.java

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequest {

    private String email;
    private String content;
    private Long thumbsUpCount;
    private Long commentsCount;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
