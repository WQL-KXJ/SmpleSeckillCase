package com.my.seckill.controller;


import com.my.seckill.util.MyApplicationContextUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DomeController {

    @RequestMapping("/home")
    public String test(Model model){
        model.addAttribute("value","环境没有问题!!");
        return "dome";
    }


    @RequestMapping("/")
    public String login(){
        MyApplicationContextUtil myApplicationContextUtil = new MyApplicationContextUtil();
        RedisTemplate redisTemplate = (RedisTemplate)myApplicationContextUtil.getBean("redisTemplate");
        System.out.println(redisTemplate);
        return "login";
    }

}
