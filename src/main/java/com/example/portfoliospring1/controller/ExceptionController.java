package com.example.portfoliospring1.controller;

import com.example.portfoliospring1.controller.response.BaseException;
import com.example.portfoliospring1.controller.response.BaseResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = 1)
public class ExceptionController {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseResponse<?> invalidRequestHandler(BaseException exception) {
        return new BaseResponse<>(exception);
    }
}
