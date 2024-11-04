package com.example.board.service;

import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import com.example.board.domain.Article;
import com.example.board.dto.AddArticleRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //게시글 추가
    public Article save(AddArticleRequest request) {
        return boardRepository.save(request.toEntity());
    }
}