package com.my.seckill.controller;


import com.my.seckill.ATO.ResposeBeanCode;
import com.my.seckill.service.impl.SeckilluserServiceImpl;
import com.my.seckill.ATO.MyCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
class SeckillserController{

    @Autowired
    SeckilluserServiceImpl seckilluserService;

    //用户初始页面
    @GetMapping("/login")
    public String login_get(){
        return "login";
    }

    //用户注册
    @PostMapping("/logon")
    @ResponseBody
    public String logon_post(@RequestParam("username")String user, @RequestParam("password")String password){

        //调用service方法进行数据插入
        boolean insert_code = seckilluserService.logon_insert(user,password);

        if(insert_code){
            return MyCode.SUCCEED.toString();
        }

        return MyCode.UNSUCCEED.toString();
    }

    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public ResposeBeanCode login_post(HttpSession session, @RequestParam("username")String username, @RequestParam("password")String password){

        //通过Shiro提供的安全工具类获取Subject主体数据
        Subject subject = SecurityUtils.getSubject();

        //将用户名和密码封装成一个Token令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);


        //建令牌放入subject主体对象
        try {
            subject.login(token);
            session.setAttribute("user",username);
            return ResposeBeanCode.success() ;
        }catch (UnknownAccountException e){
            return ResposeBeanCode.error();
        }catch (IncorrectCredentialsException e){
            return ResposeBeanCode.error();
        }

    }

}

