package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.dto.PostDto;
import gdgserver.practice.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/categories/{categoryId}/posts")
    public ResponseEntity<PostDto.PostResponseDto> addPost(@PathVariable Long categoryId,
                                                           @RequestBody PostDto.PostRequestDto request){
        PostDto.PostResponseDto response = postService.save(request, 1L, categoryId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 카테고리 내 게시글 전체 조회
    @GetMapping("/api/categories/{categoryId}/posts")
    public ResponseEntity<List<PostDto.PostResponseDto>> findPostsInCategory(@PathVariable Long categoryId){
        List<PostDto.PostResponseDto> postResponseList = postService.findPostsByCategory(categoryId);

        return ResponseEntity.ok()
                .body(postResponseList);
    }

    // 게시글 내용 조회
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostDto.PostResponseDto> findPost(@PathVariable Long postId){
        PostDto.PostResponseDto response = postService.findPostById(postId);

        return ResponseEntity.ok().body(response);
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.delete(postId);

        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<PostDto.PostResponseDto> updatePost(@PathVariable Long postId,
                                                              @RequestBody PostDto.PostRequestDto request){
        PostDto.PostResponseDto response = postService.update(postId, request);

        return ResponseEntity.ok().body(response);
    }
}
