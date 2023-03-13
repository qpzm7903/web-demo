package com.example.swagger3demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "test")
public class TestController {


    @GetMapping
    @Operation(method = "hello-world", description = "this is a simple method ", tags = "test")
    String helloWorld() {
        return "hello world";
    }
}
