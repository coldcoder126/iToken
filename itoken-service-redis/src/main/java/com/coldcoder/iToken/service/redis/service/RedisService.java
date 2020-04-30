package com.coldcoder.iToken.service.redis.service;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/24 23:48
 * @Version: 1.0
 */
public interface RedisService {
    /**
     *
     * @param key
     * @param value
     * @param seconds 超时时间(秒)
     */
    public void put(String key,Object value,long seconds);

    public Object get(String key);
}
