package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/{articleId}/{userId}/comment")
    public ResponseEntity<CommentDto.CommentResponseDto> addComment(@PathVariable Long articleId, @PathVariable Long userId, @RequestBody CommentDto.CommentRequestDto request) {
        CommentDto.CommentResponseDto commentResponseDto = commentService.save(request, userId, articleId);
        return ResponseEntity.ok(commentResponseDto);
    }

    @GetMapping("/api/{articleId}/comment")
    public ResponseEntity<List<CommentDto.CommentResponseDto>> findComments(@PathVariable Long articleId) {
        List<CommentDto.CommentResponseDto> commentResponseDtoList = commentService.findComments(articleId);
        return ResponseEntity.ok(commentResponseDtoList);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("삭제 되었습니다.");
    }
}
