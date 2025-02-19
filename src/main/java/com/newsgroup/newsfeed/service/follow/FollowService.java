package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.dto.requestDto.follow.FollowRequest;
import com.newsgroup.newsfeed.dto.responseDto.follow.FollowResponse;
import com.newsgroup.newsfeed.dto.responseDto.follow.SearchFollowerResponse;
import com.newsgroup.newsfeed.dto.responseDto.follow.UnFollowResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;

import java.util.List;

public interface FollowService {
    FollowResponse follow(FollowRequest dto);

    List<SearchFollowerResponse> searchFollowList(Users targetUser, FollowEnum followEnum);

    UnFollowResponse unFollow(Users target, Users unfollowUser);

//    Follow findByNickname(String follower, String followed);
}
