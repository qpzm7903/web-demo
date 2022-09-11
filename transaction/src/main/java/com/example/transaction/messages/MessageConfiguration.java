package com.example.transaction.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-12-7:40
 */
@Component
@Slf4j
public class MessageConfiguration {
    @Autowired
    ReloadableResourceBundleMessageSource messageSource;


    @PostConstruct
    private void init() {
        addResources("classpath*:messages/*.properties", "classpath:messages/{0}");
        addResources("classpath*:exception/*.properties", "classpath:exception/{0}");
    }

    /**
     * @param locationPattern 寻找要加载的properties文件
     * @param baseNameFormat  messageSource 需要的beanNames 样式
     */
    private void addResources(String locationPattern, String baseNameFormat) {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
            for (Resource resource : resources) {
                log.debug("load {} resource to message resource", resource.getFilename());
                messageSource.addBasenames(baseNameFormat, Objects.requireNonNull(resource.getFilename()).split("\\.")[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
