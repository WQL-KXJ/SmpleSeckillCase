package com.my.seckill.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.seckill.dao.mapper.GoodsMapper;
import com.my.seckill.dao.pojo.Goods;
import com.my.seckill.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    GoodsMapper  goodsMapper;

    @Autowired
    RedisTemplate redisTemplate;

    public  List<Goods> selectpack(){
        List<Goods> records;
        //首先查询redis是否有缓存数据
        records = (List<Goods>)redisTemplate.opsForValue().get("goods");
        if(records!=null){
            return records;
        }else {
        //分页，使用MybatisPuls提供的分页插件进行分页，一页十条数据
        Page<Goods> page = new Page<>(1,10);
        //查询条件，查询所有的话无效写条件
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","goods_name","goods_title","goods_img","goods_detail","goods_price","goods_stock");
        //分页查询后返回Page对象
        Page page1 = goodsMapper.selectPage(page, queryWrapper);
        //返回当前分页的数据
         records = page.getRecords();
        //保存到redis中
        redisTemplate.opsForValue().set("goods",records,100, TimeUnit.SECONDS);
        }
        return records;
    }


}
