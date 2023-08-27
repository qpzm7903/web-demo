package com.example.springtxdemo.controller;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Test {
    @Id
    private long id;
    private String name;
    private String address;
    private String gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String school;
}
