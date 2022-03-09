package com.my.seckill.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.my.seckill.dao.pojo.GoodsVo;
import com.my.seckill.dao.pojo.TSeckillGoods;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
public interface ITSeckillGoodsService {
    public GoodsVo selectgoodsandseckillgoods(int goodsid);

    public int getgoodscount(int id);

    public boolean  countlose(Long id);

}
