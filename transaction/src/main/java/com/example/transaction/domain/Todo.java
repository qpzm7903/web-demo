package com.example.transaction.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Integer id;
    private String name;
    private String title;
    private String content;
    private Boolean done;
}
