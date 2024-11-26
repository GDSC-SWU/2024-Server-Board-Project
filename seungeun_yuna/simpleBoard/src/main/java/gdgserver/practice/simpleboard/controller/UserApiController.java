package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.dto.UserDto;
import gdgserver.practice.simpleboard.service.UserService;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/users/signup")
    public ApiResponse<UserDto.Response> signup(@RequestBody UserDto.Request _request) {
        UserDto.Response response = userService.save(_request);
        return ApiResponse.success(response);
    }

    @PostMapping("/users/login")
    public ApiResponse<UserDto.Response> login(@RequestBody UserDto.Request _request) {
        UserDto.Response response = userService.login(_request);
        return ApiResponse.success(response);
    }

}
