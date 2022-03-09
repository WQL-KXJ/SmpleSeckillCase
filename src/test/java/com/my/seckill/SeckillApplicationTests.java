package com.my.seckill;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.seckill.dao.mapper.GoodsMapper;
import com.my.seckill.dao.mapper.SeckilluserMapper;
import com.my.seckill.dao.mapper.TSeckillGoodsMapper;
import com.my.seckill.dao.pojo.Goods;
import com.my.seckill.dao.pojo.GoodsVo;
import lombok.ToString;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class SeckillApplicationTests implements Serializable {
	@Autowired
	SeckilluserMapper seckilluserMapper;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	GoodsMapper goodsMapper;

	@Autowired
	TSeckillGoodsMapper tSeckillGoodsMapper;
//	@Test
//	void contextLoads() {
//		redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//		redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
//		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		redisTemplate.opsForValue().set("wql42","okokokokokok");
//
//	}

	@Test
	void mapper(){
		Page<Goods> page = new Page<>(1,10);
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.select("id","goods_name","goods_title","goods_img","goods_detail","goods_price","goods_stock");
		Page page1 = goodsMapper.selectPage(page, queryWrapper);
		List<Goods> records = page.getRecords();
		redisTemplate.opsForValue().set("wql",records);
		List<Goods> goods =(List<Goods>) redisTemplate.opsForValue().get("wql");
		for(Goods s:goods){

			System.out.println(s.toString());
		}
	}

	@Test
	void hh(){
		GoodsVo selectgoodsandseckillgoods = tSeckillGoodsMapper.selectgoodsandseckillgoods(1);
		long i1 = selectgoodsandseckillgoods.getStartDate().toInstant(ZoneOffset.of("+8")).toEpochMilli();
		long i= LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
		System.out.println((i1-i)/1000);
	}

	@Test
	void h(){
		System.out.println(Md5Hash.fromBase64String(UUID.randomUUID()+"123456").toHex().toString().substring(0,15));


	}
}
