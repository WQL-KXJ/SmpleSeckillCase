package com.my.seckill.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seckill")
public class CheckCode {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/captcha/{id}")
    public void captcha(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        //获取id和用户
        String user_id = request.getSession().getAttribute("user")+"_fq:"+id;
        //以用户名和id键，值为验证码保存到redis中
        redisTemplate.opsForValue().set(user_id,specCaptcha.text(),300, TimeUnit.SECONDS);
        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }

}
