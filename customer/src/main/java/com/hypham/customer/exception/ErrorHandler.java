package com.hypham.customer.exception;

import com.hypham.customer.dto.FraudCheckResponse;
import com.hypham.customer.dto.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<FraudCheckResponse> globalException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(403).body(new FraudCheckResponse<ExceptionResponse>(ResponseStatusEnum.FAIL, exceptionResponse));
    }
}
