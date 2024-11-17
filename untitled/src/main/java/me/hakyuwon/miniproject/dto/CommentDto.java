package me.hakyuwon.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {
    @Getter
    public static class CommentRequestDto {
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDto {
        private Long id;
        private Long userId;
        private Long postId;
        private String content;
    }
}
