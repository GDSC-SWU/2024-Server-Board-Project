package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.dto.LikeRequestDto;
import gdgserver.practice.simpleboard.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/like")
public class LikeApiController {

    private final LikeService likeService;

    // 좋아요 추가
    @PostMapping
    public ResponseEntity<String> addLike(@PathVariable Long postId,
                                          @RequestBody @Valid LikeRequestDto likeRequestDto) {
        likeService.insert(likeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("좋아요가 추가되었습니다.");
    }

    // 좋아요 취소
    @DeleteMapping
    public ResponseEntity<String> removeLike(@PathVariable Long postId,
                                             @RequestBody @Valid LikeRequestDto likeRequestDto) {
        likeService.delete(likeRequestDto);
        return ResponseEntity.ok().body("좋아요가 취소되었습니다.");
    }
}
