package me.hakyuwon.miniproject.repository;

import me.hakyuwon.miniproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
