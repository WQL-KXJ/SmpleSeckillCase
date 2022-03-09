package com.my.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcCofig implements WebMvcConfigurer { //组件定制化

    @Autowired
    MyHandlerInterceptor myHandlerInterceptor;

    //静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //做静态资源映射,默认SpringBoot已经配置了，
        registry.addResourceHandler("/static/*").addResourceLocations("class:/static/*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHandlerInterceptor);
    }
}
