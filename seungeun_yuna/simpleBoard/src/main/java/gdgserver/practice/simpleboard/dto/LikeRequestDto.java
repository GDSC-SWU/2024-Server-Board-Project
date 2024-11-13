package gdgserver.practice.simpleboard.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeRequestDto {

    private Long userId;
    private Long postId;

    public LikeRequestDto(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
