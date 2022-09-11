package com.example.transaction;

import com.example.transaction.domain.Todo;
import com.example.transaction.outbound.MybatisTodoRepo;
import com.example.transaction.outbound.TodoMapper;
import com.example.transaction.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TransactionApplicationTests {

    @Autowired
    TodoService todoService;

    @Autowired
    MybatisTodoRepo mybatisTodoRepo;

    @Autowired
    TodoMapper todoMapper;

    @Test
    void contextLoads() {

        Assertions.assertNotNull(todoMapper);
        Assertions.assertNotNull(todoService);
        Assertions.assertNotNull(mybatisTodoRepo);

    }

    @Test
    void test_init_todo() {
        List<Todo> list = todoMapper.list();
        Assertions.assertTrue(list.size() > 0);
        System.out.println(list);
    }

    @Test
    void test_insert_and_get() {
        Todo todo = Todo.builder().name("test1").content("test1").title("test1").done(false).build();
        todoMapper.insertTodo(todo);
        Assertions.assertNotNull(todo.getId());

        Todo byId = todoMapper.getById(todo.getId());

        Assertions.assertEquals(todo, byId);
    }

    @Test
    void test_when_insert_todo_and_then_delete_it() {

        Todo todo = Todo.builder().name("test1").content("test1").title("test1").done(false).build();
        todoMapper.insertTodo(todo);
        Assertions.assertNotNull(todo.getId());
        todoMapper.deleteById(todo.getId());

        Todo byId = todoMapper.getById(todo.getId());
        Assertions.assertNull(byId);

    }

    @Test
    void test_list() {
        List<Todo> list = todoMapper.list();
        list.stream().forEach(todo -> todoMapper.deleteById(todo.getId()));

        for (int i = 0; i < 10; i++) {
            String test = "test" + i;
            Todo todo = Todo.builder().name(test).content(test).title(test).done(false).build();
            todoMapper.insertTodo(todo);
        }

        List<Todo> list1 = todoMapper.list();

        Assertions.assertEquals(10, list1.size());
    }

    @Test
    void test_create_and_done() {
        String test = "test";
        Todo todo = Todo.builder().name(test).content(test).title(test).done(false).build();
        assert !todo.getDone();
        todoMapper.insertTodo(todo);
        assert todo.getId() != null;

        todoMapper.done(todo.getId());
        Todo byId = todoMapper.getById(todo.getId());
        assert  byId.getDone();
    }


}
