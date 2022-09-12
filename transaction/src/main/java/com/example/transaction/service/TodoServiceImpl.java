package com.example.transaction.service;

import com.example.transaction.domain.Todo;
import com.example.transaction.domain.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-10-8:30
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepo todoRepo;

    @Override
    public Todo getTodo(Integer id) {
        return todoRepo.getTodo(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Todo createTodo(Todo todo, boolean exception) {
        Todo todo1 = todoRepo.createTodo(todo);
        if (exception) {
            throw new RuntimeException();
        }
        return todo1;
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepo.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteTodo(Integer id) {
        return todoRepo.deleteTodo(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void clear() {
        todoRepo.clear();
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Todo createAndDegradation(Todo todo, boolean exception) {
        try {
            createTodo(todo, exception);
            return todo;
        } catch (Exception e) {
            System.out.println("error , and try to create once again");
        }
        createTodo(todo, false);

        return todo;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Todo createAndDoIt(Todo todo, boolean exception) {
        todo.setDone(false);
        createAndDegradation(todo, exception);
        done(todo, exception);
        return todo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void done(Todo todo, boolean exception) {
        todoRepo.done(todo.getId());
    }

    @Transactional
    @Override
    public void testException() {

        Todo build = Todo.builder().name("").title("").content("").done(false).build();
        try {
            Todo todo = testCreateA(build, true);
        } catch (RuntimeException e) {
            System.out.println("I am ok!");
        }

        testCreateB(build, false);

    }


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Todo testCreateA(Todo todo, boolean exception) {
        System.out.println("i am create A");

        Todo todo1 = todoRepo.createTodo(todo);
        if (exception) {
            throw new RuntimeException();
        }
        return todo1;
    }

    @Transactional
    @Override
    public Todo testCreateB(Todo todo, boolean exception) {

        System.out.println("I am create B");
        Todo todo1 = todoRepo.createTodo(todo);
        if (exception) {
            throw new RuntimeException();
        }
        return todo1;
    }
}
