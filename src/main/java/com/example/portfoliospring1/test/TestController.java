package com.example.portfoliospring1.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/increase-no-redis")
    public String increaseCountRedis() {
        return testService.increaseCountNoRedis();
    }

    @GetMapping("/increase-use-redis")
    public String increaseCountUseRedis() {
        return testService.increaseCountUseRedis("lock");
    }
}
