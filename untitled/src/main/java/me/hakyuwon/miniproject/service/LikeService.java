package me.hakyuwon.miniproject.service;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.domain.Like;
import me.hakyuwon.miniproject.domain.User;
import me.hakyuwon.miniproject.dto.LikeResponse;
import me.hakyuwon.miniproject.repository.BoardRepository;
import me.hakyuwon.miniproject.repository.LikeRepository;
import me.hakyuwon.miniproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    // 좋아요 생성
    public LikeResponse addLike(Long userId, Long postId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // Like 객체 생성
        Like like = Like.builder()
                .board(board)
                .user(user)
                .build();
        likeRepository.save(like); // like 객체 저장

        // from() 메서드로 LikeResponse 로 변환 후 리턴
        return LikeResponse.from(like);
    }

    // 좋아요 삭제
    public void deleteLike(Long likeId){
        likeRepository.deleteById(likeId);
    }
}
