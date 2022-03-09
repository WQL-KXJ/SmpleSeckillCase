package com.my.seckill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.seckill.dao.mapper.TOrderMapper;
import com.my.seckill.dao.pojo.TOrder;
import com.my.seckill.service.ITOrderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

}
