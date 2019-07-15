package com.ryml.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/14
 */
public class IsEmptyValidator implements ConstraintValidator<IsEmpty, Collection> {
    private String message;
    @Override
    public void initialize(IsEmpty constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Collection value, ConstraintValidatorContext context) {

           return !(value == null || value.isEmpty());

    }
}
