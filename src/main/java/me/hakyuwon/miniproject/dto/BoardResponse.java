package me.hakyuwon.miniproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponse {
    private Long post_id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public BoardResponse (Board board) {
        this.post_id=board.getPostId();
        this.title=board.getTitle();
        this.content=board.getContent();
        this.createdAt=board.getCreatedAt();

    }
}
