package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.domain.enums.Category;
import com.example.board.dto.ArticleDto;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.board.domain.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    //게시글 추가
    public ArticleDto.ArticleResponseDto save(ArticleDto.ArticleRequestDto request, Category category, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imagePath(request.getImagePath())
                .user(user)
                .category(category)
                .build();

        articleRepository.save(article);

        return ArticleDto.ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .imagePath(article.getImagePath())
                .category(article.getCategory().toString())
                .build();
    }

    //게시글 목록 조회
    public List<ArticleDto.ArticleResponseDto> findArticles(Category category) {
        List<Article> articleList = articleRepository.findAllByCategory(category);

        return articleList.stream()
                .map(article -> ArticleDto.ArticleResponseDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .imagePath(article.getImagePath())
                        .category(article.getCategory().toString())
                        .build())
                .collect(Collectors.toList());
    }

    //게시글 상세 조회
    public ArticleDto.ArticleResponseDto findArticle(long postId) {
        Article article = articleRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        return ArticleDto.ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .imagePath(article.getImagePath())
                .category(article.getCategory().toString())
                .build();
    }

    //게시글 삭제
    public void delete(long postId) {
        articleRepository.deleteById(postId);
    }

    //게시글 수정
    @Transactional
    public ArticleDto.ArticleResponseDto updateArticle(long postId, ArticleDto.ArticleRequestDto request) {
        Article article = articleRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + postId));
        article.update(request.getTitle(), request.getContent(), request.getImagePath());
        return ArticleDto.ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .imagePath(article.getImagePath())
                .category(article.getCategory().toString())
                .build();
    }
}