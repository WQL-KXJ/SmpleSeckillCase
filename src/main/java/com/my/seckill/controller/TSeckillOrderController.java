package com.my.seckill.controller;


import com.my.seckill.ATO.ResposeBeanCode;
import com.my.seckill.annotation.CurrentAnnotation;
import com.my.seckill.service.ITSeckillOrderService;
import com.my.seckill.verification.Interfacehidden;
import com.my.seckill.verification.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Controller
@RequestMapping("/tSeckillOrder")
public class TSeckillOrderController {

    @Autowired
    ITSeckillOrderService seckillOrderService;

    @Autowired
    Interfacehidden interfacehidden;

    @Autowired
    VerificationCode verificationCode;


    //隐藏的接口
    @PostMapping("/{ran}/seckillorder")
    @ResponseBody
    public ResposeBeanCode seckillorder(@RequestParam("goodsid") int goodsid,@PathVariable("ran") String ran,HttpSession httpSession){

        String user = (String) httpSession.getAttribute("user");

        Boolean checkoutinterfaceaddr = interfacehidden.checkoutinterfaceaddr(user, goodsid, ran);

        if(!checkoutinterfaceaddr){
            System.out.println("checkoutinterfaceaddr:"+checkoutinterfaceaddr);
            return ResposeBeanCode.error();
        }

        boolean seckillserver = seckillOrderService.seckillserver(goodsid, user);

        if(!seckillserver){
            return ResposeBeanCode.error();
        }
        return ResposeBeanCode.success();
    }

    //暴露的接口
    @GetMapping("/Seckillfalse/{id}/{verificat}")
    @ResponseBody
    public ResposeBeanCode seckaddre(HttpSession httpSession, @PathVariable("id") int goodsid,@PathVariable("verificat") String verificat) {

        //获取用户名
        String user = (String)httpSession.getAttribute("user");

        //验证码判断
        boolean b = verificationCode.VerificationC(user, goodsid, verificat);

        if(!b){
            return ResposeBeanCode.verificationerror();
        }

        String createinterfaceaddr = interfacehidden.createinterfaceaddr(user, goodsid);

        String path = "/tSeckillOrder/" +createinterfaceaddr+"/seckillorder";

        HashMap<String,String> map = new HashMap();
        map.put("path",path);

        return  ResposeBeanCode.addresssuccess(map) ;
    }
}

