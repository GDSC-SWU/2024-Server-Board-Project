package gdgserver.practice.simpleboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ErrorController {

    @RequestMapping("/error")
    public void error() {
        throw new IllegalArgumentException("error 테스트");
    }

}
