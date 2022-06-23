package com.zonhor.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Cron {

    @Scheduled(cron = "*/5 * * * * ?")
    public void testTasks1() throws InterruptedException {
//        System.out.println("每5秒执行一次: " + LocalDateTime.now());
    }

    @Scheduled(cron = "*/3 * * * * ?")
    public void testTasks3() {
//        System.out.println("每3秒执行一次: " + LocalDateTime.now());
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void testTasks2() {
//        System.err.println("每5分钟执行一次: " + LocalDateTime.now());
    }

}
