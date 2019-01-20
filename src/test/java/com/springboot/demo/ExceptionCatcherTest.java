package com.springboot.demo;

import com.springboot.demo.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionCatcherTest extends DemoApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        testService.service1();
    }

}
