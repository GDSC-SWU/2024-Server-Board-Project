package com.example.board.repository;

import com.example.board.domain.Article;
import com.example.board.domain.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //생성일 기준으로 내림차순 정렬(최신순)
    List<Article> findAllByCategory(Category category);
}