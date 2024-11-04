package me.hakyuwon.miniproject.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;  // BoardRepository 추가

    @Transactional
    public Comment addComment(Long postId, String content) {
        // postId에 해당하는 Board를 찾아와서 댓글과 연결
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 댓글 생성 및 게시글과 연결
        Comment comment = new Comment(board, content);
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        // 댓글 삭제
        commentRepository.deleteById(commentId);
    }
}
