package com.example.board.controller;

import com.example.board.domain.enums.Category;
import com.example.board.dto.ArticleResponse;
import com.example.board.dto.UpdateArticleRequest;
import com.example.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import com.example.board.domain.Article;
import com.example.board.dto.AddArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/posts/category/{category}")
    public ResponseEntity<Article> addArticle(@PathVariable Category category, @RequestBody AddArticleRequest request, @RequestHeader("userId") Long userId) {
        Article savedArticle = articleService.save(request, userId, category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/posts/category/{category}")
    public ResponseEntity<List<ArticleResponse>> findAllArticlesByCategory(@PathVariable Category category) {
        List<ArticleResponse> articles = articleService.findAllByCategory(category)
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/posts/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long articleId) {
        Article article = articleService.findById(articleId);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/posts/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long articleId) {
        articleService.delete(articleId);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/posts/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable long articleId, @RequestBody UpdateArticleRequest request, @RequestHeader("userId") Long userId) {
        Article updatedArticle = articleService.update(articleId, request, userId);
        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}