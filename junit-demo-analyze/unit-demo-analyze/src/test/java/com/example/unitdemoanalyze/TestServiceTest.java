package com.example.unitdemoanalyze;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2023-02-28-7:30
 */

class TestServiceTest extends BaseTestTest{

    @Autowired
    private TestService testService;


    @Test
    void test() {
        Assertions.assertEquals(1, testService.test());
    }
}