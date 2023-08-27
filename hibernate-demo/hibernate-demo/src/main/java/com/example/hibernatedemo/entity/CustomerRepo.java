package com.example.hibernatedemo.entity;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
}
