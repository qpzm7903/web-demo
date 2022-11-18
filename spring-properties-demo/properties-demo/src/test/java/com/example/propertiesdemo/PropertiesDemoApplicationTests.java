package com.example.propertiesdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Objects;
import java.util.Properties;

@SpringBootTest
class PropertiesDemoApplicationTests {


    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    TestObj test;

    @BeforeAll
    public static void init() {
        Properties properties = System.getProperties();
        properties.setProperty("test.name", "fromENV");
        System.setProperty("test.name", "FROM_ENV");
    }


    @Test
    void contextLoads() {
        assert applicationContext != null;
        assert test!= null;
        assert Objects.equals("FROM_ENV", test.getTest());

    }

}
