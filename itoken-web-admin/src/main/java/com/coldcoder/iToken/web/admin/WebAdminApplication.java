package com.coldcoder.iToken.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/20 12:09
 * @Version: 1.0
 * @EnableDiscoveryClient 让服务到服务中心注册
 */
@SpringBootApplication(scanBasePackages = "com.coldcoder.iToken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class,args);
    }
}
