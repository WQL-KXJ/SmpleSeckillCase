package com.my.seckill.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class VerificationCode {

    @Autowired
    RedisTemplate redisTemplate;
    //判断验证码
    public boolean VerificationC(String name,int goosid,String verification){

        String nan =(String) redisTemplate.opsForValue().get(name + "_fq:" + goosid);

        if(nan.equals(verification)){

            return true;
        }
        return false;
    }

}
