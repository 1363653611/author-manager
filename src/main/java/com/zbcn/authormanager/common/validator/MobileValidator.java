package com.zbcn.authormanager.common.validator;

import com.zbcn.authormanager.common.anntation.IsMobile;
import com.zbcn.authormanager.common.constant.RegexpConstant;
import com.zbcn.authormanager.utils.AuthorUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName MobileValidator.java
 * @Description 电话号码校验规则
 * @createTime 2019年08月03日 13:56:00
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }
    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(StringUtils.isBlank(value)){
            return true;
        }
        try {
            String regex = RegexpConstant.MOBILE_REG;
            return AuthorUtils.match(regex, value);
        }catch (Exception e) {
            return  false;
        }


    }
}
