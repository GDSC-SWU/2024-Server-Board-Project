package gdgserver.practice.simpleboard.jwt;

import gdgserver.practice.simpleboard.utils.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest _request,
                                        HttpServletResponse _response,
                                        AuthenticationException _exception)
            throws IOException, ServletException {

        _response.setStatus(HttpServletResponse.SC_OK); // 응답 통일을 위해 200 반환
        _response.getWriter().write("login failed");
    }
}
