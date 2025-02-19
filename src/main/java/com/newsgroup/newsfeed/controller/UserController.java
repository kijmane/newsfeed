package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.requestDto.user.UserRequest;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDto.user.UserProfileRequest;
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
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequest userRequest) {
        UserResponseDto userResponseDto = userService.registerUser(userRequest);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        boolean success = userService.loginUser(userRequest.getEmail(), userRequest.getPassword());
        if (success) {
            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 이메일 또는 비밀번호가 틀립니다.");
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserProfileById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserProfileById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<UserResponseDto> getUserProfileByNickname(@PathVariable String nickname) {
        UserResponseDto userResponseDto = userService.getUserProfileByNickname(nickname);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        UserResponseDto userResponseDto = userService.updateUserProfile(userProfileRequest);
        return ResponseEntity.ok(userResponseDto);
    }
}
