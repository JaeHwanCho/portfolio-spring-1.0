package com.example.portfoliospring1.test;

import com.example.portfoliospring1.config.RedissonLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {

    private final TestRepository testRepository;

    public String increaseCountNoRedis() {
        TestEntity testEntity = testRepository.findById(1L).get();
        testEntity.setCount(testEntity.getCount() + 1);

        return "success";
    }

    @RedissonLock(key = "#lockname")
    public String increaseCountUseRedis(String lockname) {
        TestEntity testEntity = testRepository.findById(2L).get();
        testEntity.setCount(testEntity.getCount() + 1);

        return "success";
    }
}