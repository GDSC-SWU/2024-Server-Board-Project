package gdgserver.practice.simpleboard.dto;

import gdgserver.practice.simpleboard.domain.Post;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDto {
    private Long id;
    private String title;
    private String userName;
    private String content;
    private LocalDateTime createdAt, updatedAt;
    private int view;
    private Long userId;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post){
        this.id=post.getId();
        this.title=post.getTitle();
        this.userName=post.getUser().getNickname();
        this.content=post.getContent();
        this.createdAt=post.getCreatedAt();
        this.updatedAt=post.getUpdatedAt();


    }

}
