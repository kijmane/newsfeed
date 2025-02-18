package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.requestDtos.user.UserRequestDto;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDtos.user.UserProfileRequestDto;
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
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto userRequestDto) {
        boolean success = userService.loginUser(userRequestDto.getEmail(), userRequestDto.getPassword());
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
    public ResponseEntity<UserResponseDto> updateUserProfile(@RequestBody UserProfileRequestDto userProfileRequestDto) {
        UserResponseDto userResponseDto = userService.updateUserProfile(userProfileRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
}
