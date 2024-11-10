package com.example.board.service;

import com.example.board.domain.Article;
import com.example.board.domain.User;
import com.example.board.domain.UserLike;
import com.example.board.dto.likeDto;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.LikeRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final LikeRepository likeRepository;

    public likeDto.LikeResponseDto addLike(long userId, long articleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));

        // like 만들기
        UserLike like = UserLike.builder()
                .article(article)
                .user(user)
                .build();
        likeRepository.save(like);  // 좋아요 저장

        return likeDto.LikeResponseDto.builder()
                .id(like.getId())
                .articleId(like.getArticle().getId())
                .userId(like.getUser().getId())
                .build();
    }

    // 좋아요 삭제
    public void deleteLike(long likeId) {
        likeRepository.deleteById(likeId);
    }

}
