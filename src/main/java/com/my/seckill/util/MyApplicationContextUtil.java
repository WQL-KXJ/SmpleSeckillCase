package com.my.seckill.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component//获取容器中的组件，因为特殊情况无法自动注入
public class MyApplicationContextUtil implements ApplicationContextAware {

   static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public Object getBean(String beanname){
        //获取bean
        Object bean = applicationContext.getBean(beanname);
        //返回
        return bean;
    }

}
