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
@RequiredArgsConstructor
@Service
public class LikeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository; // 필드 추가

    //좋아요 생성
    public LikeResponse addLike(long userId, long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));

        // Like 객체 생성 및 저장
        Like like = Like.builder()
                .board(board)
                .user(user)
                .build();
        likeRepository.save(like);

        // LikeResponse 객체 생성
        return LikeResponse.from(like);
    }

    // 좋아요 삭제 기능
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
