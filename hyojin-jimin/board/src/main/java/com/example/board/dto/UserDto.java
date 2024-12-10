package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    public static class UserSignupRequestDto {
        private String email;
        private String password;
        private String nickname;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSignupResponseDto {
        private String email;
        private String nickname;
    }

    @Getter
    public static class UserLoginRequestDto {
        private String email;
        private String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginResponseDto {
        private String email;
        private JwtToken token;
    }
}
