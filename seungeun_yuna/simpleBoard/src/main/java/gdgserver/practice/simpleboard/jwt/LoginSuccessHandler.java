package gdgserver.practice.simpleboard.jwt;

import gdgserver.practice.simpleboard.domain.RefreshToken;
import gdgserver.practice.simpleboard.jwt.service.JwtService;
import gdgserver.practice.simpleboard.repository.RefreshTokenRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest _request,
                                        HttpServletResponse _response,
                                        Authentication _authentication)
            throws IOException, ServletException {
        String email = extractEmail(_authentication);
        String accessToken = jwtService.createAccessToken(email);
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(_response, accessToken, refreshToken);
        refreshTokenRepository.findByUserEmail(email).ifPresent(
                refreshTokenRepository::delete
        );
        refreshTokenRepository.save(RefreshToken.builder()
                .userEmail(email)
                .refreshToken(refreshToken)
                .build()
        );

        _response.getWriter().write("\nlogin success");

        log.info("로그인 성공. email: {}", email);
        log.info("AccessToken: {}", accessToken);
        log.info("RefreshToken: {}", refreshToken);
    }

    private String extractEmail(Authentication _authentication) {
        UserDetails userDetails = (UserDetails) _authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
