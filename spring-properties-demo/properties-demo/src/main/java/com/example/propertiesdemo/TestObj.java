package com.example.propertiesdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-11-19-5:35
 */
@Component
public class TestObj {

    @Value("${test.name:fromUt}")
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
