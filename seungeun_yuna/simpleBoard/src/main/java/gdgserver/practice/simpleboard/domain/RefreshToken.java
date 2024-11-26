package gdgserver.practice.simpleboard.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public RefreshToken(String _userEmail, String _refreshToken) {
        this.userEmail = _userEmail;
        this.refreshToken = _refreshToken;
    }
}
