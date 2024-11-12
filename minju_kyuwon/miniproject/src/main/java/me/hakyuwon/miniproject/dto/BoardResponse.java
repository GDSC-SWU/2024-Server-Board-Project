package me.hakyuwon.miniproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final String title;
    private final String content;
    private final String imagePath;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public BoardResponse (Board board) {
        this.title=board.getTitle();
        this.content=board.getContent();
        this.imagePath=board.getImageUrl();
        this.createdAt=board.getCreatedAt();
        this.updatedAt=board.getUpdatedAt();
    }
}
