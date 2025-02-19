package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.dto.request.follow.FollowRequest;
import com.newsgroup.newsfeed.dto.response.follow.FollowResponse;
import com.newsgroup.newsfeed.dto.response.follow.SearchFollowerResponse;
import com.newsgroup.newsfeed.dto.response.follow.UnFollowResponse;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;

import java.util.List;

/**
 * ~가 ~를 팔로우 / 언팔로우 -> (팔로우하는 사람, 팔로우 되는 사람) 형식으로 파라미터 대입
 */
public interface FollowService {
    FollowResponse follow(FollowRequest dto);

    List<SearchFollowerResponse> searchFollowList(Users targetUser, FollowEnum followEnum);

    UnFollowResponse unFollow(Users target, Users unfollowUser);

//    Follow findByNickname(String follower, String followed);
}
