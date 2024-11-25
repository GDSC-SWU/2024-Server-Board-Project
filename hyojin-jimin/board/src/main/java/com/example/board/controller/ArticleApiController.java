package com.example.board.controller;

import com.example.board.config.jwt.JwtUtil;
import com.example.board.domain.enums.Category;
import com.example.board.dto.ArticleDto;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleDto.ArticleResponseDto> addArticle(
            @RequestBody ArticleDto.ArticleRequestDto request,
            @RequestParam(name = "category") Category category) {
        String email = JwtUtil.getCurrentUsername();
        ArticleDto.ArticleResponseDto articleResponse = articleService.save(request, category, email);
        return ResponseEntity.ok().body(articleResponse);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleDto.ArticleResponseDto>> findAllArticles(@RequestParam(name = "category") Category category) {
        List<ArticleDto.ArticleResponseDto> articles = articleService.findArticles(category);
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{articleId}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> findArticle(@PathVariable long articleId) {
        ArticleDto.ArticleResponseDto articleResponse = articleService.findArticle(articleId);
        return ResponseEntity.ok().body(articleResponse);
    }

    @DeleteMapping("/api/articles/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable long articleId) {
        articleService.delete(articleId);
        return ResponseEntity.ok().body("삭제 되었습니다");
    }

    @PutMapping("/api/articles/{articleId}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> updateArticle(@PathVariable long articleId, @RequestBody ArticleDto.ArticleRequestDto request) {
        ArticleDto.ArticleResponseDto updatedArticle = articleService.updateArticle(articleId, request);
        return ResponseEntity.ok().body(updatedArticle);
    }
}