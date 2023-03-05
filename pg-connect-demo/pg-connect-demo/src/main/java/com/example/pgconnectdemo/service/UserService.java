package com.example.pgconnectdemo.service;

import com.example.pgconnectdemo.dao.User;

import java.util.List;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2023-03-06-6:55
 */
public interface UserService {
    User findById(Long id);

    User createUser(User user);

    List<User> findAll();
}
