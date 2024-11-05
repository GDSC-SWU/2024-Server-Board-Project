package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.dto.likeDto;
import com.example.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/{articleId}/{userId}/like")
    public ResponseEntity<likeDto.LikeResponseDto> addLike(@PathVariable Long articleId, @PathVariable Long userId) {
        likeDto.LikeResponseDto likeResponseDto = likeService.addLike(userId, articleId);
        return ResponseEntity.ok(likeResponseDto);
    }

    @DeleteMapping("/api/like/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
        return ResponseEntity.ok("삭제 되었습니다.");
    }
}
