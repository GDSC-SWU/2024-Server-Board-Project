package gdgserver.practice.simpleboard.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"path", "isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private final String path;
    private final Boolean isSuccess;

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // NON_NULL -> 속성값이 null 아닌 경우에만 포함
    private final T result;


}
