package me.hakyuwon.miniproject.dto;

import lombok.*;

public class UserDto {

    @Getter
    public static class AddUserRequest {
        private String nickname;
        private String email;
        private String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddUserResponse {
        private String nickname;
        private String email;
    }

}
