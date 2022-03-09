package com.my.seckill.verification;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class Interfacehidden {

    @Autowired
    RedisTemplate redisTemplate;

    //创建隐藏接口的地址并保存
    public String createinterfaceaddr(String username,int goodid){

        String substring = Md5Hash.fromBase64String(UUID.randomUUID() + "123456").toHex().toString().substring(0, 15);

        redisTemplate.opsForValue().set(username+":"+goodid,substring,60, TimeUnit.SECONDS);

        return substring;
    }

    //效验
    public Boolean  checkoutinterfaceaddr(String username,int goodid,String md5random){

        String random= (String) redisTemplate.opsForValue().get(username + ":" + goodid);

        if(md5random.equals(random)){
            return true;
        }
        return false;
    }

}
