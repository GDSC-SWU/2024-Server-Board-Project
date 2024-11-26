package gdgserver.practice.simpleboard.converter;

import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserConverter implements Converter<UserDto.Response, User>{
    @Override
    public UserDto.Response toDto(User _user){
        return UserDto.Response.builder()
                .id(_user.getId())
                .email(_user.getEmail())
                .nickname(_user.getNickname())
                .build();
    }
}
