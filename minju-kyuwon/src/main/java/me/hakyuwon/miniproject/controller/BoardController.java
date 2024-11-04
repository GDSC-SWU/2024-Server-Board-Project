package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.dto.BoardRequest;
import me.hakyuwon.miniproject.dto.BoardResponse;
import me.hakyuwon.miniproject.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class BoardController {
    private final BoardService boardService;

    //게시글 조회
    @GetMapping("/api/posts")
    public List<Board> getBoards() { // board response 가 아니라 board로? 왜 안 됨
        return boardService.getBoards();
    }

    // 게시글 작성
    @PostMapping("/api/posts")
    public Board addPost(@RequestBody BoardRequest boardRequest) {
        return boardService.createBoard(boardRequest);
    }

}

