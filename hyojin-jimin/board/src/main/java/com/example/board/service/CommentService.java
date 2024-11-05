package com.example.board.service;

import com.example.board.domain.Article;
import com.example.board.domain.Comment;
import com.example.board.domain.User;
import com.example.board.dto.CommentDto;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // 댓글 작성
    public CommentDto.CommentResponseDto save(CommentDto.CommentRequestDto request, Long userId, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // comment로 변환
        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .article(article)
                .build();
        commentRepository.save(comment);  // 댓글 저장

        // dto로 변환해서 리턴
        return CommentDto.CommentResponseDto.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .articleId(comment.getArticle().getPostId())
                .content(comment.getContent())
                .build();
    }

    // 댓글 조회
    public List<CommentDto.CommentResponseDto> findComments(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 게시글의 댓글들 가져오기
        List<Comment> commentList = commentRepository.findAllByArticle(article);

        // dto로 변환해서 리턴
        return commentList.stream()
                .map(comment -> CommentDto.CommentResponseDto.builder()
                        .id(comment.getId())
                        .userId(comment.getUser().getId())
                        .articleId(comment.getArticle().getPostId())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    // 댓글 삭제
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

}
