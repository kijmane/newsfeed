package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.config.BCryptPasswordEncoder;
import com.newsgroup.newsfeed.dto.request.user.UserRequestDto;
import com.newsgroup.newsfeed.dto.response.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequestDto;
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
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        Users user = new Users(userRequestDto.getEmail(), userRequestDto.getNickname(), encodedPassword);
        Users savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
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
    public UserResponseDto getUserProfileById(Long id) {
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
    public UserResponseDto updateUserProfile(UserProfileRequestDto userProfileRequestDto) {
        Users user = userRepository.findByEmail(userProfileRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(userProfileRequestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD_NOT_FOUND);
        }

        // 닉네임 변경 후 저장
        user.updateNickname(userProfileRequestDto.getNewNickname());
        userRepository.save(user);

        return new UserResponseDto(user);
    }
}
