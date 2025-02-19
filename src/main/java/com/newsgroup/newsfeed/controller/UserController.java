package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.request.user.UserRequest;
import com.newsgroup.newsfeed.dto.response.user.UserResponse;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
import com.newsgroup.newsfeed.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestBody UserRequest userRequestDto) {
        boolean success = userService.loginUser(userRequestDto.getEmail(), userRequestDto.getPassword());
        if (success) {
            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 이메일 또는 비밀번호가 틀립니다.");
        }
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

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UserProfileRequest userProfileRequestDto) {
        UserResponse userResponseDto = userService.updateUserProfile(userProfileRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
}
