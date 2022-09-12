package com.example.transaction.service;

import com.example.transaction.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-10-9:48
 */
@SpringBootTest
@Slf4j
class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;

    @Test
    void test_context_load() {
        assert todoService != null;
    }

    @Test
    void test_create() {
        Todo todo = todoService.createTodo(getEmptyTodo(), false);
        assert todo.getId() != null;
    }


    @Test
    void test_create_and_throw_exception_should_rollback_and_get_noting() {
        todoService.clear();
        try {
            Todo todo = todoService.createTodo(getEmptyTodo(), true);
        } catch (Exception e) {
            log.error("", e);
        }
        List<Todo> allTodo = todoService.getAllTodo();
        assert allTodo.size() == 0;
    }

    @Test
    void test_clear_and_create() {
        Todo todo = getEmptyTodo();
        todoService.createAndDegradation(todo, true);
        assert todo.getId() != null;

    }

    /**
     * 疑问：为什么里面开启了很多require_new，但是日志里面没看到？
     */
    @Test
    void test_create_and_done() {
        Todo emptyTodo = getEmptyTodo();
        todoService.createAndDoIt(emptyTodo, true);
        assert emptyTodo.getId() != null;

        assert todoService.getTodo(emptyTodo.getId()).getDone();
    }

    @Test
    void test_exceptin() {
        todoService.clear();
        todoService.testException();
        List<Todo> allTodo = todoService.getAllTodo();
        Assertions.assertEquals(1, allTodo.size());

    }

    private Todo getEmptyTodo() {
        return Todo.builder().name("").title("").content("").done(false).build();
    }
}