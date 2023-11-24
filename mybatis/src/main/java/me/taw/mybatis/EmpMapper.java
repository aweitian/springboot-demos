package me.taw.mybatis;

import me.taw.mybatis.pojo.Emp;

import java.util.List;

public interface  EmpMapper {
    List<Emp> findAll();

    List<Emp> findAllEx();
}
