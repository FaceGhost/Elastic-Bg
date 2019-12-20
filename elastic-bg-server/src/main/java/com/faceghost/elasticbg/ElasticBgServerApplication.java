package com.faceghost.elasticbg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * tomcat 启动
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication()
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = "com.faceghost.elasticbg.mapper")
public class ElasticBgServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticBgServerApplication.class, args);
    }

}
