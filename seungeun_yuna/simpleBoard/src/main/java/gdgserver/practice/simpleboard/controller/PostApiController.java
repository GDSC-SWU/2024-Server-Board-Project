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
    @PostMapping("/categories/{category_id}/posts")
    public ResponseEntity<PostDto.PostResponseDto> addPost(@PathVariable Long category_id,
                                                           @RequestBody PostDto.PostRequestDto request){
        PostDto.PostResponseDto response = postService.save(request, 1L,category_id);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 카테고리 내 게시글 전체 조회
    @GetMapping("/categories/{category_id}/posts")
    public ResponseEntity<List<PostDto.PostResponseDto>> findPostsInCategory(@PathVariable Long category_id){
        List<PostDto.PostResponseDto> postResponseList = postService.findPostsByCategory(category_id);

        return ResponseEntity.ok()
                .body(postResponseList);
    }

    // 게시글 내용 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto.PostResponseDto> findPost(@PathVariable Long id){
        PostDto.PostResponseDto response = postService.findPostById(id);

        return ResponseEntity.ok().body(response);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.delete(id);

        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDto.PostResponseDto> updatePost(@PathVariable Long id,
                                                              @RequestBody PostDto.PostRequestDto request){
        PostDto.PostResponseDto response = postService.update(id, request);

        return ResponseEntity.ok().body(response);
    }
}
