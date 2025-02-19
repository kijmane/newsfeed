package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.dto.requestDto.user.UserRequest;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDto.user.UserProfileRequest;

public interface UserService {
    UserResponseDto registerUser(UserRequest userRequest);
    boolean loginUser(String email, String password);
    UserResponseDto getUserProfileById(Long id);
    UserResponseDto getUserProfileByNickname(String nickname);
    UserResponseDto updateUserProfile(UserProfileRequest userProfileRequest);
}
