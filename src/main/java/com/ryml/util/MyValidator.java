package com.ryml.util;

import com.ryml.annotation.NotNull;
import com.ryml.entity.Student;

import java.lang.reflect.Field;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
public class MyValidator {

    public static <T> boolean validate(T object) throws IllegalAccessException {
        if (object == null){
            throw new RuntimeException("object can not be null");
        }
        try{
            Field[] fields = object.getClass().getFields();
            for (Field field : fields) {
                NotNull annotation = field.getAnnotation(NotNull.class);
                if (annotation != null){
                    Object o = field.get(object);
                    if (o == null){
                        return false;
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        return true;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Student student = new Student();
       /* System.out.println(MyValidator.validate(student));*/
        Field id = student.getClass().getField("id");
        System.out.println(id.get(student)+"测试");

    }

}
