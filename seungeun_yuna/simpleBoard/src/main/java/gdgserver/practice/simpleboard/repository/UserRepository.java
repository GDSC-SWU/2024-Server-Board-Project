package gdgserver.practice.simpleboard.repository;

import gdgserver.practice.simpleboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
