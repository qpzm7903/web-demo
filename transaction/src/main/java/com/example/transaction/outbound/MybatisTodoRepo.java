package com.example.transaction.outbound;

import com.example.transaction.domain.Todo;
import com.example.transaction.domain.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-10-8:08
 */
@Repository
public class MybatisTodoRepo implements TodoRepo {

    @Autowired
    private TodoMapper todoMapper;
    @Override
    public Todo getTodo(Integer id) {
        return todoMapper.getById(id);
    }

    @Override
    public Todo createTodo(Todo todo) {
        Integer id = todoMapper.insertTodo(todo);
        todo.setId(id);
        return todo;
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoMapper.list();
    }

    @Override
    public boolean deleteTodo(Integer id) {
        return todoMapper.deleteById(id);
    }

    @Override
    public List<Todo> list() {
        return todoMapper.list();
    }

    @Override
    public void clear() {
        List<Todo> list = todoMapper.list();
        list.forEach(todo -> todoMapper.deleteById(todo.getId()));
    }

    @Override
    public void done(Integer id) {
        if (Objects.isNull(id)) {
            return;
        }
        todoMapper.done(id);
    }
}
