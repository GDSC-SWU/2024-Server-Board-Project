package com.example.board.controller;

import com.example.board.config.jwt.TokenProvider;
import com.example.board.dto.ArticleDto;
import com.example.board.dto.UserDto;
import com.example.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

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

    @GetMapping("/api/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/api/login";
    }
}
