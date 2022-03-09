package com.my.seckill.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.seckill.dao.mapper.TSeckillGoodsMapper;
import com.my.seckill.dao.pojo.GoodsVo;
import com.my.seckill.dao.pojo.TSeckillGoods;
import com.my.seckill.service.ITSeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Service
public class TSeckillGoodsServiceImpl implements ITSeckillGoodsService {

    @Autowired
    TSeckillGoodsMapper tSeckillGoodsMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public GoodsVo selectgoodsandseckillgoods(int goodsid) {
        GoodsVo seckillgoods;
        //从Redis中获取
        seckillgoods = (GoodsVo) redisTemplate.opsForHash().get("seckillgoods","seck:"+goodsid);

        if(seckillgoods!=null){
            return seckillgoods;
        }else{
            seckillgoods = tSeckillGoodsMapper.selectgoodsandseckillgoods(goodsid);
            redisTemplate.opsForHash().put("seckillgoods","seck:"+goodsid,seckillgoods);
            //将库存单独提前出来保存
            redisTemplate.opsForHash().put("seckillcount","seckcount:"+goodsid,seckillgoods.getStockCount());
        }
        return seckillgoods;
    }

    @Override
    public int getgoodscount(int id) {
        int getgoodscount = tSeckillGoodsMapper.getgoodscount(id);
        return getgoodscount;
    }

    @Override
    public boolean countlose(Long id) {

        boolean countlose = tSeckillGoodsMapper.countlose(id);
        return countlose;
    }


}
