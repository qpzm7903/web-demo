package com.example.mybatisdemo.domain;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherRepo {

    Teacher getById(int id);
}
