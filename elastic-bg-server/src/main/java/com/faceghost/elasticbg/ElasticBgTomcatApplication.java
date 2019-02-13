package com.faceghost.elasticbg;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * tomcat 启动
 */

@SpringBootApplication()
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = "com.faceghost.elasticbg.mapper")
public class ElasticBgTomcatApplication extends SpringBootServletInitializer{
}
