package gdgserver.practice.simpleboard.dto;

import lombok.*;


public class UserDto {
    @Getter
    public static class Request {
        private String email;
        private String password;
        private String nickname;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String nickname;
    }
}
