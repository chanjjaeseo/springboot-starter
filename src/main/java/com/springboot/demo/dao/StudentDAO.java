package com.springboot.demo.dao;

import com.springboot.demo.domain.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentDAO {

    @Insert("insert into student(id,name,sex) values(#{id},#{name},#{sex})")
    int add(Student student);

    @Update("update student set id=#{id},name=#{name} where sno=#{sex}")
    int update(Student student);

    @Delete("delete from student where id=#{id}")
    int deleteById(String sno);

    @Select("select * from student where id=#{id}")
    @Results(id = "student",value= {
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "sex", column = "sex", javaType = String.class)
    })
    Student queryStudentById(int id);

}
