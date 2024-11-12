package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Like;
import me.hakyuwon.miniproject.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/api/posts/{postId}/like")
    public ResponseEntity<Like> addLike(@PathVariable Long postId){
        Like like = likeService.addLike(postId);
        return ResponseEntity.ok()
                .body(like);
    }

    @DeleteMapping("/api/posts/{postId}/like/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long postId, @PathVariable Long likeId){
        likeService.deleteLike(likeId);
        return ResponseEntity.ok().build();
    }
}
