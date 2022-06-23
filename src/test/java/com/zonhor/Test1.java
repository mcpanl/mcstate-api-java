package com.zonhor;

import com.zonhor.utils.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class Test1 {
    @Autowired
    CacheService cacheService;

    @Test
    void test1() {

        cacheService.add("test1", "1234", 20, TimeUnit.SECONDS);
    }
}
