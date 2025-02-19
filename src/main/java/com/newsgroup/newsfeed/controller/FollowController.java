package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.request.follow.FollowRequest;
import com.newsgroup.newsfeed.dto.response.follow.FollowResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.follow.FollowService;
import com.newsgroup.newsfeed.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/users/{id}/followings")
    public void findFollow(@PathVariable String id) {

    }
}
