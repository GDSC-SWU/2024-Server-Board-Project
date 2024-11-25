package com.example.board.controller;

import com.example.board.dto.ArticleDto;
import com.example.board.dto.UserDto;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/api/signup")
    public ResponseEntity<UserDto.UserSignupResponseDto> signup(@RequestBody UserDto.UserSignupRequestDto request) {
        UserDto.UserSignupResponseDto signupResponse = userService.signup(request);
        return ResponseEntity.ok().body(signupResponse);
    }

    @PostMapping("/api/login")
    public ResponseEntity<UserDto.UserLoginResponseDto> login(@RequestBody UserDto.UserLoginRequestDto request) {
        UserDto.UserLoginResponseDto loginResponse = userService.signIn(request);
        return ResponseEntity.ok().body(loginResponse);
    }
}
