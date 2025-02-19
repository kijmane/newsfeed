package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowReqDto;
import com.newsgroup.newsfeed.dto.respondDtos.follow.FollowRespDto;
import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;

import java.util.List;

public interface FollowService {
    FollowRespDto follow(FollowReqDto dto);

    List<Users> searchFollowList(Users targetUser, FollowEnum followEnum);

    String unFollow(Users target, Users unfollowUser);

    Follow findByNickname(String follower, String followed);
}
