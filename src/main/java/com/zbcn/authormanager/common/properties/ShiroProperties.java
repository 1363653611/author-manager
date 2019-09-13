package com.zbcn.authormanager.common.properties;

import lombok.Data;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ShiroProperties.java
 * @Description 登录配置信息
 * @createTime 2019年08月03日 16:14:00
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;
}
