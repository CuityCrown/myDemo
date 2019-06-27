package com.ryml.util;

import com.ryml.annotation.NotNull;
import com.ryml.entity.Student;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
public class MyValidator {

    public static <T> ValidateResult validate(T object) throws IllegalAccessException, ClassNotFoundException {
        if (object == null){
            throw new RuntimeException("object can not be null");
        }
        ValidateResult validateResult = new ValidateResult();
        Class<?> aClass = object.getClass();
        AnnotatedType[] annotatedInterfaces = aClass.getAnnotatedInterfaces();
        for (AnnotatedType annotatedInterface : annotatedInterfaces) {
            annotatedInterface.getClass();
        }
        AnnotatedType fatherClass = aClass.getAnnotatedSuperclass();
        Class<?> aClass1 = Class.forName(fatherClass.getType().getTypeName());
        Field[] declaredFields = aClass1.getDeclaredFields();
        Field[] fields = aClass.getDeclaredFields();
        validateAnnotation(validateResult,fields,object);
        validateAnnotation(validateResult,declaredFields,object);
        return validateResult;
    }

    private static <T> void validateAnnotation(ValidateResult validateResult,Field[] fields,T object) throws IllegalAccessException {
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

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        Student student = new Student();
        System.out.println(MyValidator.validate(student));
    }

}
