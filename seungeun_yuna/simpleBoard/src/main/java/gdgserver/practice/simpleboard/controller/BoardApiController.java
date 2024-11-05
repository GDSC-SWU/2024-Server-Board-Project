package gdgserver.practice.simpleboard.controller;

import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.dto.AddPostRequest;
import gdgserver.practice.simpleboard.dto.PostResponse;
import gdgserver.practice.simpleboard.dto.UpdatePostRequest;
import gdgserver.practice.simpleboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequest request){
        Post savedPost = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> findAllPosts(){
        List<PostResponse> posts = boardService.findAll()
                .stream()
                .map(PostResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(posts);
    }

    // 게시글 내용 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> findPost(@PathVariable Integer id){
        Post post = boardService.findById(id);

        return ResponseEntity.ok().body(new PostResponse(post));
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id){
        boardService.delete(id);

        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody UpdatePostRequest request){
        Post updatedPost = boardService.update(id, request);

        return ResponseEntity.ok().body(updatedPost);
    }
}
