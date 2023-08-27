package com.example.springredisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRedisDemoApplication {
    public static void main(String[] args) {
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDFDFDFDDDDDDDDDDFDFD");
        Thread a = new Thread(() -> {
            System.out.println("dfsdfsdfsdf");
        });

        SpringApplication.run(SpringRedisDemoApplication.class, args);
    }
}