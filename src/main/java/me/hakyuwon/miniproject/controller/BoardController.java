package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.dto.BoardResponse;
import me.hakyuwon.miniproject.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;
}

