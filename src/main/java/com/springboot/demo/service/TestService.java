package com.springboot.demo.service;

import com.springboot.demo.annotation.ExceptionCatcher;
import com.springboot.demo.exception.BizException;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    @ExceptionCatcher
    public boolean service1(String str) {
        throw new BizException("业务异常1");
    }

}
