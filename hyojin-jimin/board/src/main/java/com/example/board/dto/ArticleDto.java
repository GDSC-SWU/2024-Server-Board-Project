package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ArticleDto {

    @Getter
    public static class ArticleRequestDto {
        private String title;
        private String imagePath;
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArticleResponseDto {
        private Long id;
        private String title;
        private String content;
        private String imagePath;
        private String category;
    }
}
