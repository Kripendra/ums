package com.example.ums.exception;

import com.example.ums.dto.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationException(String message){
        return ResponseEntity.badRequest().body(buildServerResponse(false, message));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> BadRequestException(String message){
        return ResponseEntity.badRequest().body(buildServerResponse(false, message));
    }

    public static ServerResponse<Object> buildServerResponse(boolean success, String message){
        return ServerResponse.builder()
                .success(success)
                .message(message)
                .build();
    }

    public static ServerResponse<Object> buildServerResponseWithData(boolean success, String message, Object userData){
        return ServerResponse.builder()
                .success(success)
                .message(message)
                .data(userData)
                .build();
    }
}
