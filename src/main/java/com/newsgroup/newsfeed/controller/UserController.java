package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.config.GetLoginUser;
import com.newsgroup.newsfeed.dto.request.user.UserRequest;
import com.newsgroup.newsfeed.dto.response.user.UserResponse;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.LoginEnum;
import com.newsgroup.newsfeed.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.newsgroup.newsfeed.enums.LoginEnum.LOGIN_USER;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequestDto) {
        UserResponse userResponseDto = userService.registerUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequestDto, HttpSession session) {
        Users user = userService.loginUser(userRequestDto.getEmail(), userRequestDto.getPassword());
        session.setAttribute(LOGIN_USER, user);

        UserResponse userResponse = new UserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserProfileById(@PathVariable Long id) {
        UserResponse userResponseDto = userService.getUserProfileById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<UserResponse> getUserProfileByNickname(@PathVariable String nickname) {
        UserResponse userResponseDto = userService.getUserProfileByNickname(nickname);
        return ResponseEntity.ok(userResponseDto);
    }

//    @PutMapping("/update")
//    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UserProfileRequest userProfileRequestDto) {
//        UserResponse userResponseDto = userService.updateUserProfile(userProfileRequestDto);
//        return ResponseEntity.ok(userResponseDto);
//    }

    @PostMapping("/update")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UserProfileRequest userProfileRequestDto,
                                                          HttpSession session) {
        Users loginUser = GetLoginUser.getLoginUser(session);
        UserResponse userResponseDto = userService.updateUserProfile(userProfileRequestDto, loginUser);
        return ResponseEntity.ok(userResponseDto);
    }
}
