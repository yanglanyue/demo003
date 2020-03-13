package com.example.demo003.config;

import com.example.demo003.component.LoginHandlerInterceptor;
import com.example.demo003.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//使用WebMvcConfigurerAdapter扩展springMVC的功能
@Configuration
//全面接管springMVC，全面接管之后整个项目会造成一些莫名其妙的错误（例如访问根目录时显示白页）
//@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("/one").setViewName("one");
    }

    @Bean//将自定义映射组建注册到容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //“/**”拦截任意路径下的任意请求，并排除掉允许的请求
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login")
                //当使用最新的webjars bootstrap 4.1.0或者4.4.1-1对项目进行渲染的时候，会出现拦截器屏蔽掉以下两个路径的问题
                // "/asserts/**"和"/webjars/**"，必须将其手动添加上
                        .excludePathPatterns("/webjars/**")
                        .excludePathPatterns("/asserts/**")
                ;
            }

            // SpringBoot重写addResourceHandlers解决resources下面静态资源无法访问
            @Override // 对静态资源进行处理，否则boot是把所有静态资源进行拦截
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/")
                        .resourceChain(true);
            }
        };
        return adapter;
    }

    @Bean//将我们自己编写的localeResolver注册到容器
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
