package com.ryml.service.impl;

import com.ryml.service.MyVolidateForFieldActuator;
import com.ryml.util.ValidationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
@Service
public class ParentVolidateForFieldActuator implements MyVolidateForFieldActuator {
    @Override
    public void validateProperties(ValidationContext validationContext) throws ClassNotFoundException, IllegalAccessException {
        Object object = validationContext.getObject();
        ValidateAnnotationActuator instance = ValidateAnnotationActuator.getInstance();
        AnnotatedType parentType = object.getClass().getAnnotatedSuperclass();
        validationContext.addClassList(Class.forName(parentType.getType().getTypeName()));
        instance.validateProperties(validationContext);
    }
}
