package com.coldcoder.iToken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: DX
 * @Description:
 * @Date: 2020/3/24 16:29
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.coldcoder.iToken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebPostsApplication {
    public static void main(String[] args) {
            SpringApplication.run(WebPostsApplication.class,args);
        }
}

