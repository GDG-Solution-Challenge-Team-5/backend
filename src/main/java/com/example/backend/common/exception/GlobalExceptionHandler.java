package com.example.backend.common.exception;


import com.example.backend.common.util.CustomResponseBody;
import com.example.backend.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseBody<Void>> handleException(Exception ex) {
        if (ex.getMessage() != null) {
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        }
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomResponseBody<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        if (ex.getMessage() != null) {
            return ResponseUtil.error(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return ResponseUtil.error(HttpStatus.BAD_REQUEST);
    }
}
