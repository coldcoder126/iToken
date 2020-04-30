package com.coldcoder.iToken.web.admin.service;

import com.coldcoder.iToken.web.admin.service.fallback.RedisCacheServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/25 20:45
 * @Version: 1.0
 */
@FeignClient(value = "itoken-service-redis",fallback = RedisCacheServiceFallback.class)
public interface RedisCacheService {

    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(@RequestParam(value = "key") String key,
                      @RequestParam(value = "value") String value,
                      @RequestParam(value = "seconds") long seconds);

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key);
}
