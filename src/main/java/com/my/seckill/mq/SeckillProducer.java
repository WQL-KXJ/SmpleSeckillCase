package com.my.seckill.mq;

import com.my.seckill.dao.pojo.TSeckillOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendmessage(TSeckillOrder tSeckillOrder){

        rabbitTemplate.convertAndSend("seckill_exchange","seckill",tSeckillOrder);

    }

}
