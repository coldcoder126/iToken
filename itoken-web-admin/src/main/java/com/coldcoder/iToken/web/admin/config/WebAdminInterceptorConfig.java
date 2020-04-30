package com.coldcoder.iToken.web.admin.config;

import com.coldcoder.iToken.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: DX
 * @Description: Interceptor的配置
 * @Date: 2020/3/11 1:12
 * @Version: 1.0
 */
@Configuration
public class WebAdminInterceptorConfig implements WebMvcConfigurer {
    @Bean
    WebAdminInterceptor webAdminInterceptor(){
        return new WebAdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webAdminInterceptor()).addPathPatterns("/**").excludePathPatterns("/static");
    }
}
