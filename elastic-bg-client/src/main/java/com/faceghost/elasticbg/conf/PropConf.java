package com.faceghost.elasticbg.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configurable
@ConfigurationProperties(prefix = "elasticbg") //只注入以app开头的属性
public class PropConf {

    @Value("${elasticbg.session-invalidate-time}")
    private Integer sessionInvalidateTime;

    @Value("${elasticbg.session-validation-interval}")
    private Integer sessionValidationInterval;

    @Value("${elasticbg.login-max-retryNumber}")
    private Integer loginMaxRetryNumber;

    @Value(("${elasticbg.login-ID-maxSession}"))
    private Integer loginIDMaxSession;

}
