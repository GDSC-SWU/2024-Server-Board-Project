package gdgserver.practice.simpleboard.service;

import gdgserver.practice.simpleboard.domain.Comment;
import gdgserver.practice.simpleboard.domain.Post;
import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.dto.CommentRequestDto;
import gdgserver.practice.simpleboard.repository.CommentRepository;
import gdgserver.practice.simpleboard.repository.PostRepository;
import gdgserver.practice.simpleboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long commentSave(String nickname, Long id, CommentRequestDto dto) {
        // 사용자 찾기
        User user = userRepository.findByNickname(nickname).orElse(null); // 값이 없으면 null 반환

        // 게시글 찾기
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        // 댓글 엔티티 생성
        Comment comment = dto.toEntity(post, user); // CommentRequestDto에서 toEntity 호출

        // 댓글 저장
        commentRepository.save(comment);

        // 댓글 ID 반환
        return comment.getId();  // 엔티티의 id 반환
    }

    // 댓글 목록 조회 메서드 수정
    public List<CommentRequestDto> getComments(Long postId) {
        // 해당 postId에 해당하는 댓글 목록을 가져옴
        List<Comment> comments = commentRepository.findByPostId(postId);

        // 댓글 엔티티를 DTO로 변환하여 반환
        return comments.stream()
                .map(CommentRequestDto::new)  // CommentRequestDto 생성자로 변환
                .collect(Collectors.toList());
    }
}
