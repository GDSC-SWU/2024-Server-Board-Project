package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.converter.UserConverter;
import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.dto.UserDto;
import gdgserver.practice.simpleboard.enums.GradeEnum;
import gdgserver.practice.simpleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    // id로 유저 조회
    public UserDto.Response findById(Long _userId){
        // id로 유저 찾기
        User user = userRepository.findById(_userId)
                .orElseThrow(()->new IllegalArgumentException("User Not Found: " + _userId));

        return userConverter.toDto(user);
    }

    // 회원가입(유저 정보 생성)
    public UserDto.Response save(UserDto.Request _request){

        User user = User.builder()
                .email(_request.getEmail())
                .password(passwordEncoder.encode(_request.getPassword())) // 패스워드 암호화
                .nickname(_request.getNickname())
                .grade(GradeEnum.D)
                .build();

        userRepository.save(user);

        return userConverter.toDto(user);
    }

    public UserDto.Response login(UserDto.Request _request){
        User user = userRepository.findByEmail(_request.getEmail())
                .orElseThrow(()->new IllegalArgumentException("User Not Found: " + _request.getEmail()));
        if(!passwordEncoder.matches(_request.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }
        return userConverter.toDto(user);
    }

    // id로 회원 삭제
    public void deleteById(Long _userId){
        // 해당 id 회원 존재하는지 찾기
        userRepository.findById(_userId)
                .orElseThrow(()->new IllegalArgumentException("User Not Found: " + _userId));
        // 회원 정보 삭제
        userRepository.deleteById(_userId);
        // 관련된 정보 함께 삭제하는 로직..?
    }

}
