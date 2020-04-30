package com.coldcoder.iToken.service.redis.service.impl;

import com.coldcoder.iToken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/24 23:50
 * @Version: 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key,value,seconds,TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        System.out.println("here is redis"+key);
        Object obj = redisTemplate.opsForValue().get(key);
        if(obj!=null) return String.valueOf(obj);
        return null;
    }
}
