package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.dto.requestDtos.user.UserRequestDto;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDtos.user.UserProfileRequestDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    boolean loginUser(String email, String password);
    UserResponseDto getUserProfile(String email);
    UserResponseDto updateUserProfile(UserProfileRequestDto userProfileRequestDto);
}