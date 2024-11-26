package gdgserver.practice.simpleboard.security;

import gdgserver.practice.simpleboard.domain.User;
import gdgserver.practice.simpleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String _email) throws UsernameNotFoundException {
        // DB 에서 이메일로 사용자 정보 조회
        User user = userRepository.findByEmail(_email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + _email));

        return new CustomUserDetails(user);
    }
}
