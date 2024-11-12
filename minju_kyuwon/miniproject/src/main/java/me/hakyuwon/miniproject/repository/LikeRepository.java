package me.hakyuwon.miniproject.repository;

import me.hakyuwon.miniproject.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
