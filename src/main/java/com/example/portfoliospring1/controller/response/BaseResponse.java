package com.example.portfoliospring1.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"statusCode", "message", "payload"})
public class BaseResponse<T> {
    private final int statusCode;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T payload;

    public BaseResponse(int statusCode, String message, T payload) {
        this.statusCode = statusCode;
        this.message = message;
        this.payload = payload;
    }

    public BaseResponse(T payload) {
        this.statusCode = BaseResponseStatusEnum.SUCCESS.getStatusCode();
        this.message = BaseResponseStatusEnum.SUCCESS.getMessage();
        this.payload = payload;
    }

    public BaseResponse(BaseException baseException) {
        this.statusCode = baseException.getStatusEnum().getStatusCode();
        this.message = baseException.getStatusEnum().getMessage();
        this.payload = (T) baseException.getStatusEnum();
    }

}



