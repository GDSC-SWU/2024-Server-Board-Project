package gdgserver.practice.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @Getter
    public static class PostRequestDto{
        private String title;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostResponseDto{
        private Long id;
        private Long userId;
        private String userName;
        private Long categoryId;
        private String title;
        private String content;
    }
}