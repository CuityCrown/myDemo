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
public class InterfaceVolidateForField implements MyVolidateForFieldActuator {

    @Override
    public void validateProperties(ValidationContext validationContext) throws ClassNotFoundException, IllegalAccessException {
        Object object = validationContext.getObject();
        //接口常量校验
        AnnotatedType[] annotatedInterfaces = object.getClass().getAnnotatedInterfaces();
        for (AnnotatedType annotatedInterface : annotatedInterfaces) {
            String typeName = annotatedInterface.getType().getTypeName();
            Class<?> interfaceClass = Class.forName(typeName);
            validationContext.addClassList(interfaceClass);
        }
    }

}
