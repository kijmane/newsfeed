package com.newsgroup.newsfeed.service;

import com.newsgroup.newsfeed.config.BCryptPasswordEncoder;
import com.newsgroup.newsfeed.entity.User;
import com.newsgroup.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    public User createUser(String email, String password, String nickname) {
        String encodedPassword = passwordEncoder.encode(password);  // 비밀번호 암호와
        User user = new User(null, email, encodedPassword, nickname, null, null);
        return userRepository.save(user);   // 사용자 저장
    }

    // 로그인
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);  // 이메일로 사용자 조회
        if(user != null && passwordEncoder.matches(password, user.getPassword())) { // 비밀번호 검증
            return user;
        }
        throw new IllegalArgumentException("email 혹은 비밀번호가 유효하지 않습니다.");    // 이메일, 비밀번호 불일치 예외발생
    }
}
