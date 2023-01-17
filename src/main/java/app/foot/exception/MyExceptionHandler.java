package app.foot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> notFoundHandler(NotFoundException exception) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(404)
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        log.error(apiResponse.toString());
        return ResponseEntity
                .status(404)
                .body(apiResponse);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> badRequestHandler(BadRequestException exception) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(400)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
        log.error(apiResponse.toString());
        return ResponseEntity
                .status(400)
                .body(apiResponse);
    }
}
