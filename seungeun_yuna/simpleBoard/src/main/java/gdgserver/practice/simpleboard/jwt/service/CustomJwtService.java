package gdgserver.practice.simpleboard.jwt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdgserver.practice.simpleboard.repository.RefreshTokenRepository;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Setter(value = AccessLevel.PRIVATE)
@Slf4j
public class CustomJwtService implements JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERNAME_CLAIM = "email";
    private static final String PREFIX = "Bearer ";

    private final RefreshTokenRepository refreshTokenRepository;

    // access token 생성
    @Override
    public String createAccessToken(String _email) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(ACCESS_TOKEN_SUBJECT)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(accessExpiration).toMillis()))
                .claim(USERNAME_CLAIM, _email)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }



    @Override
    public String createRefreshToken() {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(REFRESH_TOKEN_SUBJECT)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(refreshExpiration).toMillis()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public void deleteRefreshToken(String _email) {
        refreshTokenRepository.findByUserEmail(_email)
                .ifPresentOrElse(
                        refreshTokenRepository::delete,
                        () -> { throw new IllegalArgumentException(
                                "Refresh token not found: " + _email); }
                );

    }

    @Override
    public void sendAccessAndRefreshToken(HttpServletResponse _response,
                                          String _accessToken,
                                          String _refreshToken) throws IOException {
        _response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(_response, _accessToken);
        setRefreshTokenHeader(_response, _refreshToken);

        //Map<String, String> tokenMap = new HashMap<>();
        //tokenMap.put(ACCESS_TOKEN_SUBJECT, _accessToken);
        //tokenMap.put(REFRESH_TOKEN_SUBJECT, _refreshToken);

        //ApiResponse<Map<String, String>> result = ApiResponse.success(tokenMap);

        _response.getWriter().write("sendAccessAndRefreshToken");

    }

    @Override
    public void sendAccessToken(HttpServletResponse _response, String _accessToken) throws IOException {
        _response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(_response, _accessToken);

        //Map<String, String> tokenMap = new HashMap<>();
        //tokenMap.put(ACCESS_TOKEN_SUBJECT, _accessToken);

        //ApiResponse<Map<String, String>> result = ApiResponse.success(tokenMap);

        _response.getWriter().write("sendAccessToken");
    }

    // access token 추출
    @Override
    public Optional<String> extractAccessToken(HttpServletRequest _request) {
        return Optional.ofNullable(_request.getHeader(accessHeader)).filter(
                accessToken -> accessToken.startsWith(PREFIX)
        ).map(accessToken -> accessToken.replace(PREFIX, ""));
    }

    // refresh token 추출
    @Override
    public Optional<String> extractRefreshToken(HttpServletRequest _request) {
        return Optional.ofNullable(_request.getHeader(refreshHeader)).filter(
                refreshToken -> refreshToken.startsWith(PREFIX)
        ).map(refreshToken -> refreshToken.replace(PREFIX, ""));
    }

    // access token에서 이메일 추출
    @Override
    public Optional<String> extractEmail(String _token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(_token)
                .getBody();
        return Optional.ofNullable(claims.get(USERNAME_CLAIM, String.class));
    }

    // 토큰 유효성 검증
    @Override
    public boolean validateToken(String _token) {
        try{
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(_token);
            log.info("유효한 토큰");
            return true;
        } catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public void setAccessTokenHeader(HttpServletResponse _response, String _accessToken) {
        _response.setHeader(accessHeader, _accessToken);
    }

    @Override
    public void setRefreshTokenHeader(HttpServletResponse _response, String _refreshToken) {
        _response.setHeader(refreshHeader, _refreshToken);
    }
}
