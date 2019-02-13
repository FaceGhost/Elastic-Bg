package com.faceghost.elasticbg;

import com.anoyi.grpc.annotation.GrpcServiceScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * tomcat 启动
 */
@SpringBootApplication
@GrpcServiceScan(packages = {"com.faceghost.elasticbg.base.service"})
public class SpringBootTomcatApplication extends SpringBootServletInitializer{

}
