package com.example.swagger3demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class Swagger3demoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Swagger3demoApplication.class, args);
    }

//    @Bean
//    @Primary
//    public WebMvcRequestHandlerProvider webMvcRequestHandlerProvider(Optional<ServletContext> servletContext,
//                                                                     HandlerMethodResolver methodResolver,
//                                                                     List<RequestMappingInfoHandlerMapping> handlerMappings){
//        List<RequestMappingInfoHandlerMapping> collect = handlerMappings.stream()
//                .filter(mapping -> mapping.getPatternParser() == null)
//                .collect(Collectors.toList());
//        return new WebMvcRequestHandlerProvider(servletContext,methodResolver,collect);
//    }


}
