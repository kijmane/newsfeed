package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.dto.request.user.UserRequest;
import com.newsgroup.newsfeed.dto.response.user.UserResponse;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
import com.newsgroup.newsfeed.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        return ResponseEntity.ok(userResponse);
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();   // 세션 무효화
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserProfileById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserProfileById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<UserResponse> getUserProfileByNickname(@PathVariable String nickname) {
        UserResponse userResponse = userService.getUserProfileByNickname(nickname);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        UserResponse userResponse = userService.updateUserProfile(userProfileRequest);
        return ResponseEntity.ok(userResponse);
    }
}
