package com.cord.trs.exception;

import com.cord.trs.controller.BaseClass;
import com.cord.trs.dto.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final BaseClass baseClass;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<GlobalApiResponse> handleAppException(AppException e) {
        GlobalApiResponse response = baseClass.failure(e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
