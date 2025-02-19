package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.config.BCryptPasswordEncoder;
import com.newsgroup.newsfeed.dto.request.user.UserRequest;
import com.newsgroup.newsfeed.dto.response.user.UserResponse;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.LoginEnum;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        Users user = new Users(userRequest.getEmail(), userRequest.getNickname(), encodedPassword);
        Users savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    @Override
    public boolean loginUser(String email, String password) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute(LoginEnum.LOGIN_USER, user);
                return true;
            }
        }
        return false;
    }

    @Override
    public UserResponse getUserProfileById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponse(user);
    }

    @Override
    public UserResponse getUserProfileByNickname(String nickname) {
        Users user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponse(user);
    }

    @Override
    public UserResponse updateUserProfile(UserProfileRequest userProfileRequest) {
        Users user = userRepository.findByEmail(userProfileRequest.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(userProfileRequest.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD_NOT_FOUND);
        }

        // 닉네임 변경 후 저장
        user.updateNickname(userProfileRequest.getNewNickname());
        userRepository.save(user);

        return new UserResponse(user);
    }
}
