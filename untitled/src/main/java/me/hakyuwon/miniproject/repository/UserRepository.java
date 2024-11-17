package me.hakyuwon.miniproject.repository;

import me.hakyuwon.miniproject.domain.Comment;
import me.hakyuwon.miniproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(Long userId);
    boolean existsByEmail(String email);
}
