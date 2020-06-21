package com.example.demo003.controller;

import com.example.demo003.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    //负责接收
    @RequestMapping("/hello")
    //返回引号内的内容到浏览器，而不是thymeleaf框架规则所对应的html文件
    @ResponseBody
    public String hello(@RequestParam("user") String user){
        //45、尚硅谷_SpringBoot_web开发-定制错误数据 2:00
        if (user.equals("dangerous")){
            throw new UserNotExistException();
        }
        return "hello spring boot";
    }

//    @RequestMapping({"/","/index.html"})
//    public String topPage(){
//        return "login2";
//    }

    //在页面中查询数据并展示
    @RequestMapping("/thymeleafOne")
    public String thymeleafOne(Map<String,Object> map){
        map.put("one","<h1>1</h1>");
        map.put("two","2");
        map.put("three","3");
        map.put("users", Arrays.asList("张三","李四","王五"));
        return "thymeleafOne";
    }
}
