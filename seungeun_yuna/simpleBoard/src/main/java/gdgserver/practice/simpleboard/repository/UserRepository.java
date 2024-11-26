package gdgserver.practice.simpleboard.repository;

import gdgserver.practice.simpleboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String _email);
}
