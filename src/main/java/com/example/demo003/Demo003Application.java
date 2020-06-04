package com.example.demo003;

import com.example.demo003.config.MyMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//来标注一个主程序类，说明这是一个spring boot应用
@SpringBootApplication
public class Demo003Application {

    public static void main(String[] args) {

        //spring boot应用启动起来
        SpringApplication.run(Demo003Application.class, args);
    }

    //向主程序中自制一个ViewResolver，将其添加在容器（@Bean）
    @Bean
    public ViewResolver my(){
        return new my();
    }

    class my implements ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
