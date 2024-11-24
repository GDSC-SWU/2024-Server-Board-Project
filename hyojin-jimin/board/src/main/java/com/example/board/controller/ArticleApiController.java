package com.example.board.controller;

import com.example.board.domain.enums.Category;
import com.example.board.dto.ArticleDto;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/posts")
    public ResponseEntity<ArticleDto.ArticleResponseDto> addArticle(
            @RequestBody ArticleDto.ArticleRequestDto request,
            @RequestParam(name = "category") Category category) {
        ArticleDto.ArticleResponseDto articleResponse = articleService.save(request, category, 1L);
        return ResponseEntity.ok().body(articleResponse);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<ArticleDto.ArticleResponseDto>> findAllArticles(@RequestParam(name = "category") Category category) {
        List<ArticleDto.ArticleResponseDto> articles = articleService.findArticles(category);
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> findArticle(@PathVariable long postId) {
        ArticleDto.ArticleResponseDto articleResponse = articleService.findArticle(postId);
        return ResponseEntity.ok().body(articleResponse);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<String> deleteArticle(@PathVariable long postId) {
        articleService.delete(postId);
        return ResponseEntity.ok().body("삭제 되었습니다");
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> updateArticle(@PathVariable long postId, @RequestBody ArticleDto.ArticleRequestDto request) {
        ArticleDto.ArticleResponseDto updatedArticle = articleService.updateArticle(postId, request);
        return ResponseEntity.ok().body(updatedArticle);
    }
}