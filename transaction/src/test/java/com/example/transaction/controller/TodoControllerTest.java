package com.example.transaction.controller;

import com.example.transaction.domain.Todo;
import com.example.transaction.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-25-17:50
 */
@SpringBootTest
class TodoControllerTest {

    @Autowired
    private TodoController todoController;


    @SpyBean
    TodoService todoService;

    @Test
    void test() {
        when(todoService.getAllTodo()).thenReturn(new ArrayList<>());
        List<Todo> list = todoController.list();
        assert  list.size() == 0;
    }

    @Test
    void test2(){
        List<Todo> list = todoController.list();
        assert  list.size() == 1;
    }


}