package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService service;

//    @PostMapping("/{id}/follow")
//    public ResponseEntity<FollowRespDto> followUser(@PathVariable String id) {
//       //user서비스 findById필요
//
//
//    }

    @PostMapping("/users/{id}/followings")
    public void findFollow(@PathVariable String id) {

    }
}
