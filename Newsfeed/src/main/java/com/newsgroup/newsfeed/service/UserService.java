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

    public User createUser(String email, String password, String nickname) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, email, encodedPassword, nickname, null, null);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new IllegalArgumentException("email 혹은 비밀번호가 유효하지 않습니다.");
    }
}
