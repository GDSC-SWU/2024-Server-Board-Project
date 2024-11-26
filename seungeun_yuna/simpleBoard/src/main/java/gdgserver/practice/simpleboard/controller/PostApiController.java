package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.dto.PostDto;
import gdgserver.practice.simpleboard.service.PostService;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/categories/{categoryId}/posts")
    public ApiResponse<PostDto.Response> addPost(@PathVariable Long categoryId,
                                                 @RequestBody PostDto.Request _request){
        PostDto.Response response = postService.save(_request, 1L, categoryId);

        return ApiResponse.success(response);
    }

    // 카테고리 내 게시글 전체 조회
    @GetMapping("/categories/{categoryId}/posts")
    public ApiResponse<List<PostDto.Response>> findPostsInCategory(@PathVariable Long categoryId){
        List<PostDto.Response> postResponseList = postService.findPostsByCategory(categoryId);

        return ApiResponse.success(postResponseList);
    }

    // 게시글 내용 조회
    @GetMapping("/posts/{postId}")
    public ApiResponse<PostDto.Response> findPost(@PathVariable Long postId){
        PostDto.Response response = postService.findPostById(postId);

        return ApiResponse.success(response);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ApiResponse<?> deletePost(@PathVariable Long postId){
        postService.delete(postId);

        return ApiResponse.success("게시글 아이디 " + postId + " 삭제 완료");
    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")
    public ApiResponse<?> updatePost(@PathVariable Long postId,
                                     @RequestBody PostDto.Request _request){
        PostDto.Response response = postService.update(postId, _request);

        return ApiResponse.success(response);
    }
}
