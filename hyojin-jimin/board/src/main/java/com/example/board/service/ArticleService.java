package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.domain.enums.Category;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.board.domain.Article;
import com.example.board.dto.AddArticleRequest;
import com.example.board.dto.UpdateArticleRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    //게시글 추가
    public Article save(AddArticleRequest request, Long userId, Category category) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return articleRepository.save(request.toEntity(user, category));
    }

    //카테고리별 게시글 목록 조회
    public List<Article> findAllByCategory(Category category) {
        //최신순으로 조회
        return articleRepository.findAllByCategoryOrderByCreatedAtDesc(category);
    }

    //게시글 조회
    public Article findById(long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    //게시글 삭제
    public void delete(long articleId) {
        articleRepository.deleteById(articleId);
    }

    //게시글 수정
    @Transactional
    public Article update(long articleId, UpdateArticleRequest request, Long userId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        article.update(request.getTitle(), request.getContent(), request.getImagePath());
        return article;
    }
}