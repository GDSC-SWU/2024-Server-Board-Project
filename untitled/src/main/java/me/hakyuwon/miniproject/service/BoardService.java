package me.hakyuwon.miniproject.service;
import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.dto.BoardRequest;
import me.hakyuwon.miniproject.dto.BoardResponse;
import me.hakyuwon.miniproject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 생성
    @Transactional
    public Board save(BoardRequest boardRequest) {
        return boardRepository.save(boardRequest.toEntity());
    }

    // 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
    // 게시글 검색
    @Transactional(readOnly = true)
    public List<Board> findById(Long postId) {
        List<Board> boards = boardRepository.findByPostId(postId);
        if (boards.isEmpty()) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
        return boards;
    }

    // 게시글 수정
    @Transactional
    public Board update(Long postId, String title, String content, String imageUrl) {
        Board board = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        board.update(title, content, imageUrl);  // 엔티티 내의 update 메서드 사용
        return board;
    }

    @Transactional
    // 게시글 삭제
    public void delete(Long postId) {
        boardRepository.deleteById(postId);
    }
}
