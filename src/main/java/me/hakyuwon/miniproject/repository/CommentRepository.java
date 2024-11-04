package me.hakyuwon.miniproject.repository;

import me.hakyuwon.miniproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}