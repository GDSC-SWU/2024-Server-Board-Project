package me.hakyuwon.miniproject.controller;

import lombok.RequiredArgsConstructor;
import me.hakyuwon.miniproject.domain.Comment;
import me.hakyuwon.miniproject.dto.CommentRequest;
import me.hakyuwon.miniproject.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/post/{postId}/comment")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest) {
        // commentService를 통해 댓글 저장 로직 호출
        Comment savedComment = commentService.addComment(postId, commentRequest.getContent());
        return ResponseEntity.ok()
                .body(savedComment);
    }

    @GetMapping("/api/post/{postId}/comment")
    public ResponseEntity<List<Comment>> findComment(@PathVariable Long postId) {
        // commentService를 통해 댓글 조회 로직 호출
        List<Comment> commentList = commentService.findComments(postId);
        return ResponseEntity.ok()
                .body(commentList);
    }

    @DeleteMapping("/api/post/{postId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
