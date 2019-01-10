package com.springboot.demo.controller;

import com.springboot.demo.config.DemoProperties;
import com.springboot.demo.dao.StudentDAO;
import com.springboot.demo.dao.StudentMapper;
import com.springboot.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintController {

    @Autowired
    private DemoProperties demoProperties;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/print/test")
    String index() {
        return demoProperties.getAll();
    }

    @GetMapping("/student")
    String student() {
        return studentDAO.queryStudentById(1).toString();
    }

    @GetMapping("/student2")
    String student2() {
        Student student = studentMapper.queryStudentById(1);
        return student.toString();
    }


}
