package com.my.seckill.config;


import com.my.seckill.ATO.ResposeBeanCode;
import com.my.seckill.annotation.CurrentAnnotation;
import com.my.seckill.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserContext userContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       if(handler instanceof HandlerMethod){

           HandlerMethod handlerMethod = (HandlerMethod) handler;
           CurrentAnnotation annotation = handlerMethod.getMethodAnnotation(CurrentAnnotation.class);
            if(annotation==null){
                return true;
            }
            int second = annotation.second();
            int maxcount = annotation.maxcount();
            boolean needlogin = annotation.needlogin();
            String key =  request.getRequestURI();
            String name = (String) request.getSession().getAttribute("user");
           Object count =  redisTemplate.opsForValue().get(name + ":" + key);
           if(count.toString().equals("")){
               redisTemplate.opsForValue().set(name + ":" + key,1,second, TimeUnit.SECONDS);
           }else if((Integer)count>=maxcount){
               userContext.render(response, ResposeBeanCode.currentlimit());
               return false;
           }else{
               redisTemplate.opsForValue().increment(name + ":" + key);
               return true;
           }
       }

        return true;
    }
}
