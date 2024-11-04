package me.hakyuwon.miniproject.service;
import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
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
    public Board createBoard(String title, String content, String imageUrl) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
        return boardRepository.save(board);
    }

    // 게시글 검색 및 조회
    @Transactional(readOnly = true)
    public List<Board> getBoards(Long search) {
        return boardRepository.findByPostId(search);
    }

    // 게시글 수정
    @Transactional
    public Board updateBoard(Long boardId, String title, String content, String imageUrl) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        board.update(title, content, imageUrl);  // 엔티티 내의 update 메서드 사용
        return board;
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
