package gdgserver.practice.simpleboard.jwt.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface JwtService {
    String createAccessToken(String _email);
    String createRefreshToken();

    void deleteRefreshToken(String _email);

    void sendAccessAndRefreshToken
            (HttpServletResponse _response, String _accessToken,String _refreshToken) throws IOException;
    void sendAccessToken(HttpServletResponse _response, String _accessToken) throws IOException;

    Optional<String> extractAccessToken(HttpServletRequest _request);
    Optional<String> extractRefreshToken(HttpServletRequest _request);
    Optional<String> extractEmail(String _accessToken);

    void setAccessTokenHeader(HttpServletResponse _response, String _accessToken);
    void setRefreshTokenHeader(HttpServletResponse _response, String _refreshToken);

    boolean validateToken(String _token);
}
