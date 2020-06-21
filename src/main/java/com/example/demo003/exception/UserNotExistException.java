package com.example.demo003.exception;
//45、尚硅谷_SpringBoot_web开发-定制错误数据 2:00

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户不存在");
    }
}
