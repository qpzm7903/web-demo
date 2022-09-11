package com.example.transaction.service;

import com.example.transaction.domain.Todo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoService {
    Todo getTodo(Integer id);

    Todo createTodo(Todo todo, boolean exception);

    List<Todo> getAllTodo();

    boolean deleteTodo(Integer id);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void clear();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Todo createAndDegradation(Todo todo, boolean exception);

    @Transactional
    Todo createAndDoIt(Todo todo, boolean exception);

    @Transactional
    void done(Todo todo, boolean exception);
}
