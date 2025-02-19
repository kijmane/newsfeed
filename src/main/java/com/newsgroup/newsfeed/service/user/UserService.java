package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.dto.requestDtos.user.UserRequestDto;
import com.newsgroup.newsfeed.dto.responseDtos.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDtos.user.UserProfileRequestDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    boolean loginUser(String email, String password);
    UserResponseDto getUserProfileById(Long id);
    UserResponseDto getUserProfileByNickname(String nickname);
    UserResponseDto updateUserProfile(UserProfileRequestDto userProfileRequestDto);
}
