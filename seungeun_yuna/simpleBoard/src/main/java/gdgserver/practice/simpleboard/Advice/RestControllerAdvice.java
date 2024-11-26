package gdgserver.practice.simpleboard.Advice;

import gdgserver.practice.simpleboard.enums.ErrorStatusEnum;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.web.bind.annotation.RestControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ApiResponse<?> HandlerIllegalArgumentException
            (IllegalArgumentException e, HttpServletRequest request) {
        return ApiResponse.failure(request.getRequestURI(),e.getMessage());
    }
}
