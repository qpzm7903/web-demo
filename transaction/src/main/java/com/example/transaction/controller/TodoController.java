package com.example.transaction.controller;

import com.example.transaction.domain.Todo;
import com.example.transaction.domain.repo.TodoRepo;
import com.example.transaction.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * todo description
 *
 * @author qpzm7903 * @since 2022-09-10-8:07
 */
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoRepo repo;

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> list() {
        return todoService.getAllTodo();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        repo.createTodo(todo);
        return todo;
    }

    @PostMapping("/done")
    public Todo createAndDone(@RequestBody Todo todo) {
        return todoService.createAndDoIt(todo, true);

    }
}
