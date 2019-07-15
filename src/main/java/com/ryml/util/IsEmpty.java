package com.ryml.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/14
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsEmptyValidator.class})
public @interface IsEmpty {
    String message() default "集合不能为空 ";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
