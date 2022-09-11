package com.example.transaction.outbound;

import com.example.transaction.domain.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-09-10-8:09
 */
@Mapper
public interface TodoMapper {

    @Select("select * from todo where id = #{id}")
    Todo getById(Integer id);


    Integer insertTodo(@Param("todo") Todo todo);


    @Select("select *from todo")
    List<Todo> list();

    @Delete("delete from todo where id =  #{id}")
    boolean deleteById(Integer id);


    @Update("update todo set done=1 where id  = ${id}")
    void done(Integer id);
}
