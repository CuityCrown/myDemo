package com.ryml.service.impl;

import com.ryml.annotation.NotNull;
import com.ryml.service.Validator;
import com.ryml.util.ValidateResult;
import com.ryml.util.ValidationContext;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.List;

/**
 * description:通过属性上添加的注解对实体类进行参数校验
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28 20:53
 */
public class FieldsValidator implements Validator {

    @Override
    public <T> ValidationContext initValidationContext(T object) throws ClassNotFoundException {
        ValidationContext validationContext = new ValidationContext(object);

        //本体类Field获取
        Class<?> aClass = validationContext.getObject().getClass();
        Field[] NoumenonFields = aClass.getDeclaredFields();
        validationContext.addField(NoumenonFields);

        //本体类实现接口Field获取
        AnnotatedType[] annotatedInterfaces = aClass.getAnnotatedInterfaces();
        for (AnnotatedType annotatedInterface : annotatedInterfaces) {
            String typeName = annotatedInterface.getType().getTypeName();
            Field[] interfaceFields = Class.forName(typeName).getDeclaredFields();
            validationContext.addField(interfaceFields);
        }

         //本体类父类Field获取
        AnnotatedType parentType = aClass.getAnnotatedSuperclass();
        Field[] parentFields = Class.forName(parentType.getType().getTypeName()).getDeclaredFields();
        validationContext.addField(parentFields);

        return validationContext;
    }

    @Override
    public void validate(ValidationContext validationContext) throws IllegalAccessException {
        Object object = validationContext.getObject();
        ValidateResult validateResult = validationContext.getValidateResult();
        List<Field> fields = validationContext.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            NotNull annotation = field.getAnnotation(NotNull.class);
            Object property = field.get(object);
            if (annotation != null && property == null){
                validateResult.setResult(false);
                validateResult.addMessage(annotation.message());
            }
        }
    }

}
