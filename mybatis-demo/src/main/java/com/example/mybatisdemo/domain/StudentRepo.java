package com.example.mybatisdemo.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentRepo {
    Student getById(@Param("id") int id);

    List<String> getNames();
}

