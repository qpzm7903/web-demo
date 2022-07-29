package com.example.demo.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class TestController {

    @GetMapping("/test")
    List<Student> test() {
        List<Student> list = new ArrayList<>();
        Random random = new Random();
        int i = random.nextInt(1, 10);
        for (int j = 0; j < i; j++) {
            list.add(Student.builder()
                    .name("random name" + random.nextInt())
                    .id("" + random.nextInt())
                    .build());
        }
        System.out.println("log" + System.currentTimeMillis());
        return list;
    }

    @Data
    @Builder
    static class Student {
        String name;
        String id;
    }
}
