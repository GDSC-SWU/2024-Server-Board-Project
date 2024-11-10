package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.dto.PostDto;
import gdgserver.practice.simpleboard.service.PostService;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/categories/{categoryId}/posts")
    public ResponseEntity<ApiResponse<PostDto.PostResponseDto>> addPost(@PathVariable Long categoryId,
                                               @RequestBody PostDto.PostRequestDto request){
        PostDto.PostResponseDto response = postService.save(request, 1L, categoryId);

        return ResponseEntity.ok()
                .body(ApiResponse.<PostDto.PostResponseDto>builder()
                    .isSuccess(true)
                    .code("200 OK")
                    .message("요청 성공")
                    .result(response)
                    .build());
    }

    // 카테고리 내 게시글 전체 조회
    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<ApiResponse<List<PostDto.PostResponseDto>>> findPostsInCategory(@PathVariable Long categoryId){
        List<PostDto.PostResponseDto> postResponseList = postService.findPostsByCategory(categoryId);

        return ResponseEntity.ok()
                .body(ApiResponse.<List<PostDto.PostResponseDto>>builder()
                        .isSuccess(true)
                        .code("200 OK")
                        .message("요청 성공")
                        .result(postResponseList)
                        .build());
    }

    // 게시글 내용 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse<PostDto.PostResponseDto>> findPost(@PathVariable Long postId){
        PostDto.PostResponseDto response = postService.findPostById(postId);

        return ResponseEntity.ok()
                .body(ApiResponse.<PostDto.PostResponseDto>builder()
                    .isSuccess(true)
                    .code("200 OK")
                    .message("요청 성공")
                    .result(response)
                    .build());
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable Long postId){
        postService.delete(postId);

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                    .isSuccess(true)
                    .code("200 OK")
                    .message("요청 성공")
                    .result("게시글 아이디 " + postId + " 삭제 완료")
                    .build());
    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@PathVariable Long postId,
                                                  @RequestBody PostDto.PostRequestDto request){
        PostDto.PostResponseDto response = postService.update(postId, request);

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .isSuccess(true)
                        .code("200 OK")
                        .message("요청 성공")
                        .result(response)
                        .build());
    }
}
