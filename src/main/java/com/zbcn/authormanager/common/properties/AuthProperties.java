package com.zbcn.authormanager.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName AuthProperties.java
 * @Description 登录配置类
 * @createTime 2019年08月03日 16:11:00
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:auth.properties"})
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
    private ShiroProperties shiro = new ShiroProperties();
    private boolean openAopLog = true;
}
