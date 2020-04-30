package com.coldcoder.iToken.service.redis.controller;

import com.coldcoder.iToken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/24 23:54
 * @Version: 1.0
 */
@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(String key,String value,long seconds){
        redisService.put(key,value,seconds);
        return "ok";
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(String key){
        Object o = redisService.get(key);
        if(o!=null){
            String json = String.valueOf(o);
            System.out.println(json);
            return json;
        }
        return null;
    }
}
