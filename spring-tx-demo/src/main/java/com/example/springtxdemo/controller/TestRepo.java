package com.example.springtxdemo.controller;

import org.springframework.data.repository.CrudRepository;

public interface TestRepo extends CrudRepository<Test, Long> {
}
