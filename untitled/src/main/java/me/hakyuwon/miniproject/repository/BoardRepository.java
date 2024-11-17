package me.hakyuwon.miniproject.repository;

import me.hakyuwon.miniproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByPostId(Long postId);
}
