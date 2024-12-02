package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.dto.UserDto;
import me.hakyuwon.miniproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/users/signup")
    public ResponseEntity<UserDto.AddUserResponse> signup(@RequestBody UserDto.AddUserRequest request){
        UserDto.AddUserResponse userResponse = userService.save(request);
        // 회원가입 후 로그인 페이지로
        return ResponseEntity.ok().body(userResponse);
    }
    // 회원 탈퇴

}
