package app.foot.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiResponse {
    private Integer code;
    private HttpStatus httpStatus;
    private String message;
}
