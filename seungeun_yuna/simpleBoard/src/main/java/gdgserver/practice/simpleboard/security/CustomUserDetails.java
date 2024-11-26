package gdgserver.practice.simpleboard.security;

import gdgserver.practice.simpleboard.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한 반환
        return Collections.singleton(() -> "ROLE_" + user.getGrade().getGradeName());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // 이메일, 비밀번호로 인증 처리할거라 email 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 확인 로직
        return true; // 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 확인 로직
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 인증 정보 만료 확인 로직
        return true; // 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        // 활성 여부 확인 로직
        return true;
    }
}
