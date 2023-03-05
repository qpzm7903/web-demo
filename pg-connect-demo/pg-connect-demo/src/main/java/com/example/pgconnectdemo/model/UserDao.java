package com.example.pgconnectdemo.model;

import com.example.pgconnectdemo.dao.User;
import org.springframework.data.repository.CrudRepository;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2023-03-06-6:54
 */
public interface UserDao extends CrudRepository<User, Long> {}