package me.taw.mybatis.pojo;

import lombok.Data;

@Data
public class Emp {
    private Integer id;
    private String name;
    private String job;
    private Double salary;
    private Integer dept_id;
    private String dept;
}
