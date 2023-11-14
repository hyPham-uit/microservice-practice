package com.hypham.learn.customer.exception;

import com.hypham.learn.clients.dto.DefaultResponse;
import com.hypham.learn.clients.dto.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class) // enhance more in future
    public ResponseEntity<DefaultResponse> fraudException(Exception ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse<>(ResponseStatusEnum.FAIL, exceptionResponse));
    }
}
