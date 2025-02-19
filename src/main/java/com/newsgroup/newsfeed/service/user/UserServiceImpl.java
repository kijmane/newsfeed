package com.newsgroup.newsfeed.service.user;

import com.newsgroup.newsfeed.config.BCryptPasswordEncoder;
import com.newsgroup.newsfeed.dto.requestDtos.user.UserRequestDto;
import com.newsgroup.newsfeed.dto.responseDto.user.UserResponseDto;
import com.newsgroup.newsfeed.dto.requestDtos.user.UserProfileRequestDto;
import com.newsgroup.newsfeed.entity.Users;
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
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public UserResponseDto getUserProfileById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다."));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserProfileByNickname(String nickname) {
        Users user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 닉네임의 사용자가 없습니다."));
        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserProfile(UserProfileRequestDto userProfileRequestDto) {
        Users user = userRepository.findByEmail(userProfileRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자가 없습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(userProfileRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 닉네임 변경 후 저장
        user.updateNickname(userProfileRequestDto.getNewNickname());
        userRepository.save(user);

        return new UserResponseDto(user);
    }
}
