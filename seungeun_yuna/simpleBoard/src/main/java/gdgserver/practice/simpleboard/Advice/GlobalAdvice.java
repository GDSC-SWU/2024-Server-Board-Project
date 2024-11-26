package gdgserver.practice.simpleboard.Advice;

import gdgserver.practice.simpleboard.utils.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ApiResponse<?> HandlerIllegalArgumentException
            (IllegalArgumentException e, HttpServletRequest request) {
        return ApiResponse.failure(request.getRequestURI(),e.getMessage());
    }
}
