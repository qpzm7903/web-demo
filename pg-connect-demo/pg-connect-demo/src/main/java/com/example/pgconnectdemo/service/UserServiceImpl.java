package com.example.pgconnectdemo.service;

import com.example.pgconnectdemo.dao.User;
import com.example.pgconnectdemo.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2023-03-06-6:55
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        Iterable<User> all = userDao.findAll();
        List<User> result = new ArrayList<>();
        all.forEach(result::add);
        return result;
    }

}