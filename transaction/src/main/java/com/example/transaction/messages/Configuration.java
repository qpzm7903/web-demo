package com.example.transaction.messages;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-12-8:23
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }
}
