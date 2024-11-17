package me.hakyuwon.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hakyuwon.miniproject.domain.Like;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {
    private Long likeId;
    private Long userId;
    private Long postId;

    public static LikeResponse from(Like like){
        return new LikeResponse(
                like.getLikeID(),
                like.getUser().getUserID(),
                like.getBoard().getPostId()
        );
    }
}
