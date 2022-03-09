package com.my.seckill.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.seckill.dao.pojo.Seckilluser;
import com.my.seckill.util.MySlatUtil;
import com.my.seckill.util.MyTimeUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WQL
 * @since 2021-12-18
 */
@Service
public class SeckilluserServiceImpl implements Serializable {


    //用户注册service
    public boolean logon_insert(String username,String password) {
        //获取随机盐
        String slat = MySlatUtil.getSlat(8);
        //对密码进行加密，散列次数为1024
        Md5Hash md5Hash = new Md5Hash(password, slat,1024);
        //加密后的密码
        String Md5Password = md5Hash.toHex();
        //pojo对象
        Seckilluser seckilluser = new Seckilluser();
        seckilluser.setUsername(username);
        seckilluser.setPassword(Md5Password);
        seckilluser.setPasswordslat(slat);
        seckilluser.setRegisterDate(MyTimeUtil.getTime());
        //使用MybatisPlus ActiveRecord的方式进行插入数据
        boolean insert = seckilluser.insert();
        return insert;
    }

    //判断用户是否存在
    public Seckilluser userdecide(String username){
        Seckilluser seckilluser = new Seckilluser();
        //条件构造器
        QueryWrapper<Seckilluser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        //查询到返回实体类，否则返回NUll
        Seckilluser seckilluser1 = (Seckilluser)seckilluser.selectOne(queryWrapper);
        return seckilluser1;
    }

    //通过用户名返回用户ID
    public Long getuserid(String name){
        Seckilluser seckilluser = new Seckilluser();
        QueryWrapper<Seckilluser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",name);
        Seckilluser seckilluser1 = seckilluser.selectOne(queryWrapper);
        return seckilluser1.getId();
    }

}