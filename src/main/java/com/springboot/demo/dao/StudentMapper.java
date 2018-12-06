package com.springboot.demo.dao;

import com.springboot.demo.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

    Student queryStudentById(int id);

}
