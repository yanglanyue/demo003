package com.example.demo003.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 登陆检查，没有登陆的用户无法访问相关网页
* */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前做一个预检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //loginUser的相关属性为空，不合法，重新返回登录页
            request.setAttribute("msg","没有权限，请先登录。");//并且显示错误消息
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            //已登录，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
