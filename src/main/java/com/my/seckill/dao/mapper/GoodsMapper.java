package com.my.seckill.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.my.seckill.dao.pojo.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WQL
 * @since 2022-02-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Override
    <E extends IPage<Goods>> E selectPage(E page,  @Param("ew")Wrapper<Goods> queryWrapper);
}
