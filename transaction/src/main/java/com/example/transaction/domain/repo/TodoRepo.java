package com.example.transaction.domain.repo;

import com.example.transaction.domain.Todo;

import java.util.List;

public interface TodoRepo {

    Todo getTodo(Integer id);

    Todo createTodo(Todo todo);

    List<Todo> getAllTodo();

    boolean deleteTodo(Integer id);

    List<Todo> list();

    void clear();

    void done(Integer id);
}
