package com.my.seckill.dao.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.seckill.dao.pojo.Seckilluser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.mybatis.caches.redis.RedisCache;
import org.springframework.stereotype.Repository;


//查询时使用缓存
@Repository
//@CacheNamespace(implementation = RedisCache.class,eviction =RedisCache.class)
public interface SeckilluserMapper extends BaseMapper<Seckilluser> {

    @Override
    int insert(Seckilluser entity);

    @Override
    Seckilluser selectOne(@Param("ew") Wrapper<Seckilluser> queryWrapper);
}
