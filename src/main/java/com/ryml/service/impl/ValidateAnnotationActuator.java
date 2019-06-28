package com.ryml.service.impl;

import com.ryml.annotation.NotNull;
import com.ryml.util.ValidateResult;
import com.ryml.util.ValidationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
public class ValidateAnnotationActuator {

    private volatile static ValidateAnnotationActuator validateAnnotationActuator;

    public static ValidateAnnotationActuator getInstance(){
        if (validateAnnotationActuator == null){
            validateAnnotationActuator = new ValidateAnnotationActuator();
        }
        return validateAnnotationActuator;
    }

    public <T> void validateProperties(ValidationContext validationContext) throws IllegalAccessException {
        List<Class<T>> classList = validationContext.getClassList();
        Object object = validationContext.getObject();
        ValidateResult validateResult = validationContext.getValidateResult();
        for (Class<T> tClass : classList) {
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                NotNull annotation = field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                if (annotation != null){
                    Object o = field.get(object);
                    if (o == null){
                        validateResult.setResult(false);
                        validateResult.addMessage(annotation.message());
                    }
                }
            }
        }
    }

}
