package gdgserver.practice.simpleboard.Advice;

import gdgserver.practice.simpleboard.enums.ErrorStatusEnum;
import gdgserver.practice.simpleboard.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.web.bind.annotation.RestControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiResponse<?>> HandlerIllegalArgumentException(IllegalArgumentException e,
                                                                          HttpServletRequest request) {
        System.out.println("IllegalArgumentException error");
        ApiResponse<?> response = ApiResponse.builder()
                .path(request.getRequestURI())
                .isSuccess(false)
                .code(ErrorStatusEnum.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.ok().body(response);
    }
}
