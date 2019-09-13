package com.zbcn.authormanager.common.anntation;

import com.zbcn.authormanager.common.constant.LimitType;

import java.lang.annotation.*;

/**
 * @ClassName Limit.java
 * @Description 限制类注解
 * @author zbcn8
 * @version 1.0.0
 * @createTime 2019年07月21日 11:09:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {

    /**
     * 资源名称，用于描述接口功能
     * @return
     */
    String name() default "";
    /**
     * 资源 key
     */
    String key() default "";

    /**
     * key prefix
     */
    String prefix() default "";

    /**
     * 时间范围，单位秒
     */
    int period();

    /**
     * 限制访问次数
     */
    int count();

    /**
     * 限制类型
     */
    LimitType limitType() default LimitType.CUSTOMER;

}
