package com.coldcoder.iToken.web.admin.service.fallback;

import com.coldcoder.iToken.web.admin.service.RedisCacheService;
import org.springframework.stereotype.Component;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/25 20:49
 * @Version: 1.0
 */
@Component
public class RedisCacheServiceFallback implements RedisCacheService {

    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
