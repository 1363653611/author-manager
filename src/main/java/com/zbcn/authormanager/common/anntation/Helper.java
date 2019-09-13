package com.zbcn.authormanager.common.anntation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName Helper.java
 * @Description 用来给注解属性期别名
 * @createTime 2019年08月03日 12:12:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Helper {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
