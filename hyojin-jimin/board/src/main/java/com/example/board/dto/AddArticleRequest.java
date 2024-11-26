package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.board.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;
    private String imagePath;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .imagePath(imagePath)
                .build();
    }
}