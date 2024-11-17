package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.dto.CommentDto;
import me.hakyuwon.miniproject.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/api/{postId}/{userId}/comment")
    public ResponseEntity<CommentDto.CommentResponseDto> addComment(@RequestBody CommentDto.CommentRequestDto requestDto, @PathVariable Long postId, @PathVariable Long userId) {
        CommentDto.CommentResponseDto responseDto = commentService.save(requestDto, postId, userId);
        return ResponseEntity.ok(responseDto);
    }

    // 모든 댓글 조회
    @GetMapping("/api/{postId}")
    public ResponseEntity<List<CommentDto.CommentResponseDto>> getComments(@PathVariable Long postId) {
        // dto 객체로 변환하여 반환
        List<CommentDto.CommentResponseDto> commentResponseDtoList = commentService.findComments(postId);
        return ResponseEntity.ok(commentResponseDtoList);
    }

    // 댓글 삭제
    @DeleteMapping("/api/{postID}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
