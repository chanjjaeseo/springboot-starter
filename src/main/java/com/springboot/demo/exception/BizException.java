package com.springboot.demo.exception;

public class BizException extends RuntimeException {

    public BizException(String msg) {
        super(msg);
    }

}
