package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.config.BCryptPasswordEncoder;
import com.newsgroup.newsfeed.dto.request.user.UserRequest;
import com.newsgroup.newsfeed.dto.response.user.UserResponse;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
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
    public UserResponse registerUser(UserRequest userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        Users user = new Users(userRequestDto.getEmail(), userRequestDto.getNickname(), encodedPassword);
        Users savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    @Override
    public Users loginUser(String email, String password) {
        Optional<Users> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            boolean isMatch = passwordEncoder.matches(password, user.getPassword());

            if (isMatch) {
                return user;
            } else {
                throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD_NOT_FOUND);
            }
        } else {
            throw new CustomException(ErrorCode.UNAUTHORIZED_EMAIL_NOT_FOUND);
        }
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
    public UserResponse updateUserProfile(UserProfileRequest userProfileRequestDto) {
        Users user = userRepository.findByEmail(userProfileRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(userProfileRequestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        // 닉네임 변경 후 저장
        user.updateNickname(userProfileRequestDto.getNewNickname());
        userRepository.save(user);

        return new UserResponse(user);
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
