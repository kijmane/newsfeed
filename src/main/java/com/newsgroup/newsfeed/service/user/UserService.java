package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.dto.requestDto.user.UserRequest;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponse;
import com.newsgroup.newsfeed.dto.requestDto.user.UserProfileRequest;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    boolean loginUser(String email, String password);
    UserResponse getUserProfileById(Long id);
    UserResponse getUserProfileByNickname(String nickname);
    UserResponse updateUserProfile(UserProfileRequest userProfileRequest);
}
