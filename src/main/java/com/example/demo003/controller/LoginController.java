package com.example.demo003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    //之前@RequestMapping的写法
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    //新写法@PostMapping
    @PostMapping(value = "/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Map<String,Object> map,
            HttpSession session
    ){
        //判断登陆成功
        //登录成功
        if(!StringUtils.isEmpty(username)&&"dangerous".equals(password)){
            //创建一个session获取登陆者相关信息，以检测登陆者合法性
            session.setAttribute("loginUser",username);
            //登陆成功，防止表单重复提交，可以重定向到主页
            return "redirect:/main.html";
        }else {
            //登录失败
            map.put("msg","登录失败，用户名或密码错误。");
            return "login2";
        }
    }
}
