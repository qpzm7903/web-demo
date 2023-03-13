package com.example.swagger2demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@ApiModel(value = "test")
public class TestController {


    @GetMapping
    @ApiOperation(value = "hello-world", notes = "this is a simple method ", tags = "test")
    String helloWorld() {
        return "hello world";
    }
}
