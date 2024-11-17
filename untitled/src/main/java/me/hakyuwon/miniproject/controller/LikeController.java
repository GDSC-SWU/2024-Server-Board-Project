package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.dto.LikeResponse;
import me.hakyuwon.miniproject.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // 좋아요 생성
    @PostMapping("/api/{postId}/{userId}/like")
    public ResponseEntity<LikeResponse> addLike(@PathVariable Long postId, @PathVariable Long userId) {
        LikeResponse likeResponse = likeService.addLike(postId, userId);
        return ResponseEntity.ok(likeResponse);
    }

    // 좋아요 삭제
    @DeleteMapping("/api/{postId}")
    public ResponseEntity<String> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
        return ResponseEntity.ok("삭제되었습니다.");
    }
}
