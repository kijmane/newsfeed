package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.dto.request.user.UserRequest;
import com.newsgroup.newsfeed.dto.response.user.UserResponse;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
import com.newsgroup.newsfeed.entity.Users;

public interface UserService {
    UserResponse registerUser(UserRequest userRequestDto);
    Users loginUser(String email, String password);
    UserResponse getUserProfileById(Long id);
    UserResponse getUserProfileByNickname(String nickname);
    UserResponse updateUserProfile(UserProfileRequest userProfileRequestDto);

    Users findById(Long id);
}
