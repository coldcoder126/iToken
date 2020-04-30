package com.coldcoder.iToken.service.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: DX
 * @Description: 同时是服务提供者和服务消费者,消费redis,供用户消费
 * @Date: 2020/2/25 12:11
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.coldcoder.iToken")
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = "com.coldcoder.iToken.service.sso.mapper")
public class ServiceSSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSSOApplication.class,args);
    }
}
