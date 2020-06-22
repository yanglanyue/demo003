package com.example.demo003.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {

    //Filter的初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter process...");
        chain.doFilter(request,response);

    }

    //Filter的销毁
    @Override
    public void destroy() {

    }
}
