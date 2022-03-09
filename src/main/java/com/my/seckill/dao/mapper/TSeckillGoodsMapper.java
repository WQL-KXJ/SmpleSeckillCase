package com.my.seckill.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.seckill.dao.pojo.GoodsVo;
import com.my.seckill.dao.pojo.TSeckillGoods;

import java.util.List;


public interface TSeckillGoodsMapper {

    public GoodsVo selectgoodsandseckillgoods(int id);

    public int getgoodscount(int goodsid);

    public boolean countlose(Long goodsid);
}
