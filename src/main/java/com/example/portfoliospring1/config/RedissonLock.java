package com.example.portfoliospring1.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {
    // 락 이름
    String key();

    // 시간 단위
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    // 락 대기시간
    long waitTime() default 1000L;

    // 락 임대시간
    long leaseTime() default 3L;
}
