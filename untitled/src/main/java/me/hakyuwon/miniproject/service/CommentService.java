package me.hakyuwon.miniproject.service;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.domain.Comment;
import me.hakyuwon.miniproject.domain.User;
import me.hakyuwon.miniproject.dto.CommentDto;
import me.hakyuwon.miniproject.repository.BoardRepository;
import me.hakyuwon.miniproject.repository.CommentRepository;
import me.hakyuwon.miniproject.repository.UserRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //댓글 작성
    @Transactional
    public CommentDto.CommentResponseDto save(CommentDto.CommentRequestDto requestDto, Long userId, Long postId ) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // comment로 변환
        Comment comment = Comment.builder()
                .board(board)
                .user(user)
                .content(requestDto.getContent())
                .build();
        commentRepository.save(comment);

        // dto로 변환 후 리턴
        return CommentDto.CommentResponseDto.builder()
                .id(comment.getId())
                .userId(comment.getUser().getUserID())
                .postId(comment.getBoard().getPostId())
                .content(comment.getContent())
                .build();
    }

    //대댓글 작성
    @Transactional
    public Comment addReply(Long postId, Long parentId, Long userId, String content) {
        // 게시글 존재 확인
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 부모 댓글 존재 확인
        Comment parentComment = commentRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("부모 댓글을 찾을 수 없습니다."));

        // 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 대댓글 생성
        Comment reply = Comment.builder()
                .board(board)
                .parent(parentComment)  // 부모 댓글 설정
                .user(user)  // 작성자 설정
                .content(content)
                .build();

        return commentRepository.save(reply);
    }

    //댓글 수정


    //댓글 조회
    public List<Comment> findComments(Long postId) {
        // 게시글 존재 여부 확인
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 게시글에 해당하는 댓글 리스트 조회
        return commentRepository.findByPostId(postId);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        // 댓글 삭제
        commentRepository.deleteById(commentId);
    }
}
