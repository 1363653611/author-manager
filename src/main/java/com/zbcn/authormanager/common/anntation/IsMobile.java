package com.zbcn.authormanager.common.anntation;


import com.zbcn.authormanager.common.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName IsMobile.java
 * @Description 是否为电话
 * @createTime 2019年08月03日 13:53:00
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
public @interface IsMobile {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
