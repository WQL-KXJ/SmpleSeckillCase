package com.my.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.seckill.dao.pojo.TSeckillOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
public interface ITSeckillOrderService extends IService<TSeckillOrder> {

    public boolean seckillserver(int goodsid,String username);

}
