package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.domain.Like;
import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.dto.LikeRequestDto;
import gdgserver.practice.simpleboard.repository.LikeRepository;
import gdgserver.practice.simpleboard.repository.PostRepository;
import gdgserver.practice.simpleboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void insert(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not found user id : " + likeRequestDto.getUserId()));

        Post post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not found post id : " + likeRequestDto.getPostId()));

        // 이미 좋아요되어있으면 409 에러 반환
        if (likeRepository.findByUserAndPost(user, post).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already liked this post");
        }

        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();

        likeRepository.save(like);

        postRepository.updateCount(post, true); // 좋아요 추가 시 카운트 증가
    }

    @Transactional
    public void delete(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not found user id : " + likeRequestDto.getUserId()));

        Post post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not found post id : " + likeRequestDto.getPostId()));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not found like for user and post"));

        likeRepository.delete(like);

        postRepository.updateCount(post, false); // 좋아요 삭제 시 카운트 감소
    }
}
