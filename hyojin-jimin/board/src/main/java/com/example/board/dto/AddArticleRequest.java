package com.example.board.dto;

import com.example.board.domain.User;
import com.example.board.domain.enums.Category;
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
    private Long userId;
    private Category category;

    public Article toEntity(User user, Category category) {
        return Article.builder()
                .title(title)
                .content(content)
                .imagePath(imagePath)
                .user(user)
                .category(category)
                .build();
    }
}