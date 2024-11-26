package gdgserver.practice.simpleboard.jwt;

import gdgserver.practice.simpleboard.jwt.service.JwtService;
import gdgserver.practice.simpleboard.repository.RefreshTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private final String LOGIN_URI = "/api/users/login"; // /login으로 오는 요청에 대해서는 처리 x

    @Override
    protected void doFilterInternal(HttpServletRequest _request,
                                    HttpServletResponse _response,
                                    FilterChain _filterChain)
            throws ServletException, IOException {

        // 로그인 uri 일 경우 다음 필터로 넘김
        if(_request.getRequestURI().equals(LOGIN_URI)) {
            _filterChain.doFilter(_request, _response);
            return;
        }

        log.info("jwt 필터 작동");
        String refreshToken = jwtService
                .extractRefreshToken(_request)
                .filter(jwtService::validateToken) // 유효한 토큰인지 체크
                .orElse(null);

        // 유효한 리프레시 토큰이 온 경우
        if(refreshToken != null){
            checkAndReIssueToken(_response, refreshToken);
            return; // 인증 처리는 진행 X
        }
        tryAuthentication(_request, _response, _filterChain);
    }

    // refresh token을 확인해서 db에 존재하면 새 accessToken을 response 헤더에 담아 전송
    private void checkAndReIssueToken(HttpServletResponse _response, String _refreshToken) {
        refreshTokenRepository.findByRefreshToken(_refreshToken)
                .ifPresent(token -> {
                    try {
                        jwtService.sendAccessToken(
                                _response, jwtService.createAccessToken(token.getUserEmail())
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

    }

    private void tryAuthentication(HttpServletRequest _request,
                                   HttpServletResponse _response,
                                   FilterChain _filterChain)
        throws ServletException, IOException {

        // request에서 access Token 추출
        String accessToken = jwtService.extractAccessToken(_request)
                .filter(jwtService::validateToken) // 유효한 토큰인지 확인
                .orElse(null);
        if(accessToken != null){
            String email = jwtService.extractEmail(accessToken).orElse(null);
            if(email != null){
                if(refreshTokenRepository.existsByUserEmail(email)){
                    saveAuthentication(accessToken);
                }
            }
        }
        _filterChain.doFilter(_request, _response);
    }

    private void saveAuthentication(String _token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractEmail(_token).orElse(null));

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails, _token,
                        authoritiesMapper.mapAuthorities(userDetails.getAuthorities())
                );

        //SecurityContext context = SecurityContextHolder.createEmptyContext();
        //context.setAuthentication(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("authenticated user: {}", userDetails.getUsername());
    }

}