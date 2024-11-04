package com.example.board.service;

import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import com.example.board.domain.Article;
import com.example.board.dto.AddArticleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //게시글 추가
    public Article save(AddArticleRequest request) {
        return boardRepository.save(request.toEntity());
    }

    //게시글 목록 조회
    public List<Article> findAll() {
        //최신순으로 조회
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    //게시글 조회
    public Article findById(long postId) {
        return boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + postId));
    }
}