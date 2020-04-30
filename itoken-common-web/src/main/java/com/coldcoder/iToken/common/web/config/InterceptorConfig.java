package com.coldcoder.iToken.common.web.config;

import com.coldcoder.iToken.common.web.interceptor.ConstantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: DX
 * @Description: 拦截器配置
 * @Date: 2020/3/10 20:00
 * @Version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConstantInterceptor()).addPathPatterns("/**");
    }
}
