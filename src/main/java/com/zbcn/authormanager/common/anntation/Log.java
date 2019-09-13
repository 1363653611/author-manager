package com.zbcn.authormanager.common.anntation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName Log.java
 * @Description 日志记录类
 * @createTime 2019年08月03日 19:45:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String value() default "";
}
