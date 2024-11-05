package com.example.board.dto;

import lombok.Getter;
import com.example.board.domain.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;
    private final String imagePath;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.imagePath = article.getImagePath();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}