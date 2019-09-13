package com.zbcn.authormanager.common.constant;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName RegexpConstant.java
 * @Description 正则表达式的常量类
 * @createTime 2019年08月03日 14:02:00
 */
public class RegexpConstant {
    // 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
    public static final String MOBILE_REG = "[1]\\d{10}";
}
