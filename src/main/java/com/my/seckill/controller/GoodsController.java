package com.my.seckill.controller;


import com.my.seckill.dao.pojo.Goods;
import com.my.seckill.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/goodspack",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String goods(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        //查看redis是否有缓存页面
        String goods_html = (String) redisTemplate.opsForValue().get("goods_html");

        if(ObjectUtils.isEmpty(goods_html)){
            List<Goods> selectpack = goodsService.selectpack();
            //将分页数据存入域中
            model.addAttribute("goods_pack",selectpack);
            //将httpServletRequest，httpServletResponse，ServletContext和域中的数据保存到WebContext中
            WebContext webContext = new WebContext(httpServletRequest,httpServletResponse,httpServletRequest.getServletContext(),httpServletRequest.getLocale(),model.asMap());
            //页面名称和webContext对象
            goods_html = thymeleafViewResolver.getTemplateEngine().process("goods",webContext);
            //保存到redis中
            if(!ObjectUtils.isEmpty(goods_html)){
                redisTemplate.opsForValue().set("goods_html",goods_html,1000, TimeUnit.SECONDS);
            }
        }
        return goods_html;
    }

}

