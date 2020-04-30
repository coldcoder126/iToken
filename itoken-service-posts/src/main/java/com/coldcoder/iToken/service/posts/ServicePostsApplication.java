package com.coldcoder.iToken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: DX
 * @Description:
 * @Date: 2020/3/23 11:54
 * @Version: 1.0
 */
@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@MapperScan({"com.coldcoder.iToken.common.mapper","com.coldcoder.iToken.service.posts.mapper"})
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class,args);
    }
}
