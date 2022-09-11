package com.example.transaction.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-12-6:37
 */

@SpringBootTest
class ApplicationContextUtilTest {

    @Autowired
    ApplicationContextUtil applicationContextUtil;

    @Test
    void test_application_context() {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        Environment environment = ApplicationContextUtil.getEnvironment();

        assert applicationContext != null;

        assert environment != null;
    }


    @Test
    void test_load_resource_bundle() {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        String test1 = applicationContext.getMessage("test1", null, Locale.CHINA);
    }
}