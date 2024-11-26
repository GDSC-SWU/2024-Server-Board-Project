package gdgserver.practice.simpleboard.repository;

import gdgserver.practice.simpleboard.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserEmail(String email);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    boolean existsByUserEmail(String email);
}
