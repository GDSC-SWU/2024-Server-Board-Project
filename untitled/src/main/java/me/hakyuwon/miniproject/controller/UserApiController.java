package me.hakyuwon.miniproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.dto.AddUserRequest;
import me.hakyuwon.miniproject.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/users/signup")
    public String signup(AddUserRequest request){
        userService.save(request);
        // 회원가입 후 로그인 페이지로
        return "redirect:/login";
    }

    // 로그아웃
    @GetMapping("/api/users/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        // 로그아웃 이후 로그인 페이지로
        return "redirect:/login";
    }

    // 회원 탈퇴

}
