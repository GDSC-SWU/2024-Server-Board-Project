package me.hakyuwon.miniproject.service;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.User;
import me.hakyuwon.miniproject.dto.AddUserRequest;
import me.hakyuwon.miniproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // 비밀번호 암호화

    // user 엔티티 객체 생성, 저장
    public long save(AddUserRequest dto){
        // 이메일 중복 검증
        if (userRepository.existsByEmail(dto.getEmail())){
        throw new IllegalArgumentException("이미 등록된 이메일입니다.");}

        return userRepository.save(User.builder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getUserID();
    }

    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}
