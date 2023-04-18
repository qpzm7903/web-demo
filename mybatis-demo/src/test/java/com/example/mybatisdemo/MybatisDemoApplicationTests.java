package com.example.mybatisdemo;

import com.example.mybatisdemo.domain.Student;
import com.example.mybatisdemo.domain.StudentRepo;
import com.example.mybatisdemo.domain.Teacher;
import com.example.mybatisdemo.domain.TeacherRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisDemoApplicationTests {
    @Autowired
    private TeacherRepo teacherRepo;


    @Autowired
    private StudentRepo studentRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void test_get_student() {
        Student byId = studentRepo.getById(1);
        Assertions.assertNotNull(byId);
    }

    @Test
    void test_get_names() {
        List<String> names = studentRepo.getNames();
        System.out.println(names);
        Assertions.assertTrue(names.size() > 1);
    }

}
