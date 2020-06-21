package com.example.demo003.controller;

import com.example.demo003.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

//    浏览器客户端返回的都是json，无法实现针对不同客户端返回相应的数据或者页面类型
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notExist");
//        map.put("message",e.getMessage());
//        return map;
//    }

//    实现针对不同客户端返回相应的数据或者页面类型
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        //传入我们自己的错误状态码:4xx 5xx，系统就会调取我们自己定制的相应页面
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notExist");
        map.put("message",e.getMessage());

        //返回到SpringMVC自带的error页面相关处理机制，由系统判断返回相应的数据或者页面类型
        return "forward:/error";
    }
}
