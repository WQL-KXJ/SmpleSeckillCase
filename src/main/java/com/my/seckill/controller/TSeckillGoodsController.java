package com.my.seckill.controller;


import com.my.seckill.dao.pojo.GoodsVo;
import com.my.seckill.service.impl.TSeckillGoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Controller
@RequestMapping("/tSeckillGoods")
public class TSeckillGoodsController {

    @Autowired
    TSeckillGoodsServiceImpl seckillGoodsServiceImpl;


    @GetMapping("/Seckill/{id}")
    public String SelectSeckillGoods(Model model, @PathVariable("id") int goodsid){

        GoodsVo goodsVo = seckillGoodsServiceImpl.selectgoodsandseckillgoods(goodsid);
        model.addAttribute("seck",goodsVo);

        //秒杀的状态(0表示开始，1表示未开始，2表示结束)
        int seckillstatus =0;
        //秒杀倒计时
        long seckilltime = -1;
        LocalDateTime startdate = goodsVo.getStartDate();
        LocalDateTime enddate = goodsVo.getEndDate();
        LocalDateTime nowdate = LocalDateTime.now();
        if(nowdate.isBefore(startdate)){
            seckillstatus=1;
            seckilltime= (startdate.toInstant(ZoneOffset.of("+8")).toEpochMilli() - nowdate.toInstant(ZoneOffset.of("+8")).toEpochMilli())/1000;
        }else if (nowdate.isAfter(enddate)){
            seckillstatus=2;
        }
        model.addAttribute("seckillstatus",seckillstatus);
        model.addAttribute("seckilltime",seckilltime);
        return "seckillgoods";
    }



}

