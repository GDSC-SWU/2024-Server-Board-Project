package me.hakyuwon.miniproject.service;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Board;
import me.hakyuwon.miniproject.dto.BoardRequest;
import me.hakyuwon.miniproject.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BlogRepository blogRepository;

    // 글 추가
    public Board save(BoardRequest request){
        return blogRepository.save(request.toEntity());
    }
}
