package me.hakyuwon.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String nickname;
    private String email;
    private String password;
}
