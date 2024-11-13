package gdgserver.practice.simpleboard.dto;

import gdgserver.practice.simpleboard.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {

    private Long id;

    private String content;

    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private String updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private String nickname;

    private Long postId;


        public CommentResponseDto(Comment comment) {

            this.id = comment.getId();
            this.content = comment.getContent();
            this.createdAt = comment.getCreatedAt();

            this.updatedAt = comment.getUpdatedAt();

            this.nickname = comment.getUser().getNickname();

            this.postId = comment.getPost().getId();
        }
}
