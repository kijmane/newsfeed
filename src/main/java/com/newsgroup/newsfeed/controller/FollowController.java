package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowReqDto;
import com.newsgroup.newsfeed.dto.respondDtos.FollowRespDto;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.follow.FollowService;
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

    private final FollowService service;

//    @PostMapping("/{nickname}/follow")
//    public ResponseEntity<FollowRespDto> followUser(@PathVariable String nickname) {
//
//    }

    @PostMapping("/users/{username}/followings")
    public void findFollow(@PathVariable String username) {
        service.findByNickname()
    }
}
