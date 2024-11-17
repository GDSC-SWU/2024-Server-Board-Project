package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.dto.BoardRequest;
import me.hakyuwon.miniproject.dto.BoardResponse;
import me.hakyuwon.miniproject.dto.UpdateBoardRequest;
import me.hakyuwon.miniproject.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class BoardController {
    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/api/posts")
    public ResponseEntity<Board> addBoard(@RequestBody BoardRequest boardRequest) {
        Board SavedBoard = boardService.save(boardRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SavedBoard);
    }

    //게시글 조회
    @GetMapping("/api/posts")
    public ResponseEntity<List<BoardResponse>> findAllBoard() {
        List<BoardResponse> board = boardService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(board);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable long postId){
        List<Board> board = boardService.findById(postId);

        return ResponseEntity.ok()
                .body(new BoardResponse((Board) board));
    }

    //게사글 삭제
    @DeleteMapping("api/posts/{postId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long postId){
        boardService.delete(postId);

        return ResponseEntity.ok()
                .build();
    }

    //게사글 수정
    @PutMapping("api/posts/{postId}")
    public ResponseEntity<Board> updateBoard(@PathVariable long postId, @RequestBody UpdateBoardRequest request) {
        Board updatedBoard = boardService.update(postId, request.getTitle(), request.getContent(), request.getImageUrl());

        return ResponseEntity.ok()
                .body(updatedBoard);
    }


}
