package com.example.springtxdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/tests")
public class TestController {


    @Autowired
    private TestRepo testRepo;

    @GetMapping("/list")
    List<Test> list() {
        Iterable<Test> all = testRepo.findAll();
        List<Test> collect = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        return collect;
    }

    @PostMapping
    @Transactional
    public Test create(@RequestBody Test test) {
        Test save = testRepo.save(test);
        if ("fail".equals(test.getName())) {
            throw new RuntimeException("test fail and rollback");
        }
        return save;
    }

    @DeleteMapping("{id}")
    @Transactional
    public int delete(@PathVariable("id") Long id) {
        if (Objects.isNull(id)) {
            return 0;
        }
        testRepo.deleteById(id);
        return 1;
    }

}
