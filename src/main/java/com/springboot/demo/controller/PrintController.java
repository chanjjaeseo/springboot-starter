package com.springboot.demo.controller;

import com.springboot.demo.conf.DemoProperties;
import com.springboot.demo.dao.StudentDAO;
import com.springboot.demo.dao.StudentMapper;
import com.springboot.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintController {

    @Autowired
    private DemoProperties demoProperties;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/print/test")
    String index() {
        return demoProperties.getAll();
    }

    @RequestMapping("/student")
    String student() {
        return studentDAO.queryStudentById(1).toString();
    }

    @RequestMapping("/student2")
    String student2() {
        Student student = studentMapper.queryStudentById(1);
        return student.toString();
    }


}
