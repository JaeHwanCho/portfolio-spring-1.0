package com.example.portfoliospring1.controller.response;


import lombok.Getter;

@Getter
public enum BaseResponseStatusEnum {
    SUCCESS(20000, "요청에 성공했습니다."),
    NOT_FOUND_DATA(40040, "데이터가 존재하지 않습니다."),
    DUPLICATED_NICKNAME(40040, "중복된 닉네임입니다."),
    DUPLICATED_EMAIL(40040, "이미 존재하는 e-mail입니다."),

    INVALID_EMAIL(40040, "유효하지 않은 e-mail입니다."),
    EXPIRED_CODE(40040, "만료된 코드입니다."),
    INVALID_CODE(40040, "틀린 코드입니다."),
    ;

    private final int statusCode;
    private final String message;

    BaseResponseStatusEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
