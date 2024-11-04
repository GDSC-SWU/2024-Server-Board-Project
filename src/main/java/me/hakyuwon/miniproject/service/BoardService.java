package me.hakyuwon.miniproject.service;
import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.dto.BoardRequest;
import me.hakyuwon.miniproject.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BlogRepository blogRepository;

    // 게시글 생성
    @Transactional
    public Board createBoard(String title, String content, String imageUrl) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
        return blogRepository.save(board);
    }

    // 게시글 검색 및 조회
    @Transactional(readOnly = true)
    public List<Board> getBoards(String search) {
        return blogRepository.findByTitleContainingOrContentContaining(search, search);
    }

    // 게시글 수정
    @Transactional
    public Board updateBoard(Long boardId, String title, String content, String imageUrl) {
        Board board = blogRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        board.update(title, content, imageUrl);  // 엔티티 내의 update 메서드 사용
        return board;
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long boardId) {
        blogRepository.deleteById(boardId);
    }
}
