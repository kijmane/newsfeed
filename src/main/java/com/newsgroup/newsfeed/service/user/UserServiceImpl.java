package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.config.BCryptPasswordEncoder;
import com.newsgroup.newsfeed.dto.requestDto.user.UserRequest;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDto.user.UserProfileRequest;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.exception.CustomException;
import com.newsgroup.newsfeed.exception.ErrorCode;
import com.newsgroup.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        Users user = new Users(userRequest.getEmail(), userRequest.getNickname(), encodedPassword);
        Users savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    @Override
    public boolean loginUser(String email, String password) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public UserResponseDto getUserProfileById(java.lang.Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserProfileByNickname(String nickname) {
        Users user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserProfile(UserProfileRequest userProfileRequest) {
        Users user = userRepository.findByEmail(userProfileRequest.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(userProfileRequest.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        // 닉네임 변경 후 저장
        user.updateNickname(userProfileRequest.getNewNickname());
        userRepository.save(user);

        return new UserResponseDto(user);
    }
}
