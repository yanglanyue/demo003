package com.example.demo003.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/*
 * 可以在链接上携带区域信息（页面实现语言点击选项选择）
 * */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getParameter("language");

        //判断language是否为空，空值则返回系统默认值
        Locale locale = Locale.getDefault();

        //判断language是否为空，不为空
        if(!StringUtils.isEmpty(language)){
            //将字符串以“_”分割
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
