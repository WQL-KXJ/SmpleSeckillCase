package com.my.seckill.security.shiro_redis;

import com.my.seckill.util.MyApplicationContextUtil;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

public class MyCache<k,v> implements Cache<k,v> {

    @Override
    public v get(k k) throws CacheException {
//        RedisTemplate redistemplate = getredistemplate();
//        redistemplate.setKeySerializer(new StringRedisSerializer());
//        //注意：这个v泛型是底层自己传入的，尽量不要用Object，使用泛型即可
//        v o =(v) redistemplate.opsForValue().get(k.toString());
        //将对象返回

        return null;
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("put:"+k);
        System.out.println("put:"+v);
        //将认证的数据保存到redis中
//        RedisTemplate redistemplate = getredistemplate();
//        redistemplate.setKeySerializer(new StringRedisSerializer());
//        redistemplate.opsForValue().set(k.toString(), v);
        //这个设置缓存不需要返回数据

        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }

    public RedisTemplate getredistemplate(){

        MyApplicationContextUtil myApplicationContextUtil = new MyApplicationContextUtil();
        RedisTemplate template = (RedisTemplate) myApplicationContextUtil.getBean("redisTemplate");

        return template;
    }


}