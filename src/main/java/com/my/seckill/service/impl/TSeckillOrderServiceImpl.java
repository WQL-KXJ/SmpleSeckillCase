package com.my.seckill.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.seckill.dao.mapper.TSeckillOrderMapper;
import com.my.seckill.dao.pojo.GoodsVo;
import com.my.seckill.dao.pojo.TSeckillOrder;
import com.my.seckill.mq.SeckillProducer;
import com.my.seckill.service.ITSeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Service
public class TSeckillOrderServiceImpl extends ServiceImpl<TSeckillOrderMapper, TSeckillOrder> implements ITSeckillOrderService {


    @Autowired
    SeckilluserServiceImpl seckilluserService;

    @Autowired
    TSeckillGoodsServiceImpl seckillGoodsService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SeckillProducer seckillProducer;

    static Long getuserid;

    public boolean seckillserver(int goodsid,String username){

        SessionCallback sessionCallback =new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //在session获取用户，假如为空就表示未登录，不能下订单
                if(ObjectUtils.isEmpty(username)){
                    return false;
                }

                //判断秒杀的库存是否有存货
                GoodsVo seckillgoods = (GoodsVo) redisTemplate.opsForHash().get("seckillgoods", "seck:" + goodsid);
                if(seckillgoods.getStockCount() <= 0){
                    return false;
                }
                redisTemplate.watch("seckillcount");

                //通过用户名获取用户ID
                 getuserid = seckilluserService.getuserid(username);

                //通过用户ID判断秒杀订单表是否被重复秒杀
                QueryWrapper<TSeckillOrder> queryWrapper = new QueryWrapper<>();
                HashMap map = new HashMap();
                map.put("user_id",getuserid);
                map.put("goods_id",goodsid);
                queryWrapper.allEq(map);
                TSeckillOrder one = getOne(queryWrapper);
                if(!ObjectUtils.isEmpty(one)){
                    return false;
                }
                operations.multi();
                redisTemplate.opsForHash().increment("seckillcount", "seckcount:" + goodsid,1);

                List exec = operations.exec();
                if(exec.isEmpty()){

                    return false;
                }
                return true;
            }
        };
        boolean execute = (boolean) redisTemplate.execute(sessionCallback);
        if(execute){
        TSeckillOrder order = new TSeckillOrder();
        order.setUserId(getuserid);
        order.setGoodsId((long) goodsid);
        order.setOrderId(UUID.randomUUID().toString());
        seckillProducer.sendmessage(order);
        redisTemplate.opsForHash().delete("seckillcount", "seckcount:" + goodsid);
        redisTemplate.opsForHash().delete("seckillgoods", "seck:" + goodsid);}
        return execute;
    }
}