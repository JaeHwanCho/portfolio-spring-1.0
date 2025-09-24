package com.example.portfoliospring1.controller.response;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    public final BaseResponseStatusEnum statusEnum;

    public BaseException(BaseResponseStatusEnum baseResponseStatusEnum) {
        this.statusEnum = baseResponseStatusEnum;
    }
}
