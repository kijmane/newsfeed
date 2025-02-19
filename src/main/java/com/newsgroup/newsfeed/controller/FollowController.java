package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.request.follow.FollowRequest;
import com.newsgroup.newsfeed.dto.response.follow.FollowResponse;
import com.newsgroup.newsfeed.dto.response.follow.SearchFollowerResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.follow.FollowService;
import com.newsgroup.newsfeed.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.newsgroup.newsfeed.config.GetLoginUser.getLoginUser;
import static com.newsgroup.newsfeed.enums.FollowEnum.FOLLOWERS;
import static com.newsgroup.newsfeed.enums.FollowEnum.FOLLOWINGS;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/{id}/follow")
    public ResponseEntity<FollowResponse> findFollow(@PathVariable Long id, HttpSession session) {
        Users loginUser = getLoginUser(session);
        Users targetUser = getTargetUser(id);

        FollowResponse followResult = followService.follow(
                new FollowRequest(loginUser, targetUser)
        );

        return ResponseEntity.ok().body(followResult);
    }

    @PostMapping("/{id}/followers")
    public ResponseEntity<List<SearchFollowerResponse>> findFollowList(@PathVariable Long id) {
        Users targetUser = getTargetUser(id);
        List<SearchFollowerResponse> searchFollowerResponses = followService.searchFollowList(targetUser, FOLLOWERS);

        return ResponseEntity.ok().body(searchFollowerResponses);
    }

    @PostMapping("/{id}/followings")
    public ResponseEntity<List<SearchFollowerResponse>> findFollowingList(@PathVariable Long id) {
        Users targetUser = getTargetUser(id);
        List<SearchFollowerResponse> searchFollowerResponses = followService.searchFollowList(targetUser, FOLLOWINGS);

        return ResponseEntity.ok().body(searchFollowerResponses);
    }

    @PostMapping("/{id}/unfollow")
    public void unfollowUser(@PathVariable Long id, HttpSession session) {
        Users targetUser = getTargetUser(id);
        followService.unFollow(getLoginUser(session), targetUser);
    }

    private Users getTargetUser(Long id) {
        return userService.findById(id);
    }
}
