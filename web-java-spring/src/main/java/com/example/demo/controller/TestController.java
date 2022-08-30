package com.example.demo.controller;

import com.example.demo.utils.ApplicationUtils;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.swagger2.web.Swagger2ControllerWebMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class TestController {

    @Autowired
    private Swagger2ControllerWebMvc swagger2ControllerWebMvc;

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
//        Object nam = ApplicationUtils.getBean("nam");
        ResponseEntity<Json> documentation = swagger2ControllerWebMvc.getDocumentation(null, null);

        System.out.println(documentation.getBody().toString());
        return list;
    }

    @Data
    @Builder
    static class Student {
        String name;
        String id;
    }
}
