package com.my.seckill.mq;

import com.my.seckill.dao.pojo.TSeckillOrder;
import com.my.seckill.service.ITSeckillGoodsService;
import com.my.seckill.service.ITSeckillOrderService;
import com.my.seckill.service.impl.TSeckillGoodsServiceImpl;
import com.my.seckill.service.impl.TSeckillOrderServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SeckillConsumer {

    @Autowired
    ITSeckillOrderService seckillOrderService;

    @Autowired
    ITSeckillGoodsService seckillGoodsService;

    @Autowired
    RedisTemplate redisTemplate;

    @RabbitListener(queues = "seckill_queue")
    public void messagelistener(TSeckillOrder tSeckillOrder){

        //库存减一
        seckillGoodsService.countlose(tSeckillOrder.getGoodsId());

        boolean update = seckillOrderService.save(tSeckillOrder);
    }
}
