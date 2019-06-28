package com.ryml.util;

import com.ryml.annotation.NotNull;
import com.ryml.entity.Student;
import com.ryml.service.MyVolidateForFieldActuator;
import com.ryml.service.impl.InterfaceVolidateForField;
import com.ryml.service.impl.NoumenonVolidateForField;
import com.ryml.service.impl.ParentVolidateForFieldActuator;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
public class MyValidator {

    private static List<MyVolidateForFieldActuator> actuators;

    static {
        actuators = new ArrayList<>();
        actuators.add(new InterfaceVolidateForField());
        actuators.add(new NoumenonVolidateForField());
        actuators.add(new ParentVolidateForFieldActuator());
    }

    public static <T> ValidateResult validate(T object) throws IllegalAccessException, ClassNotFoundException {
        if (object == null){
            throw new RuntimeException("object can not be null");
        }
        ValidationContext validationContext = new ValidationContext();
        validationContext.setObject(object);
        for (MyVolidateForFieldActuator actuator : actuators) {
            actuator.validateProperties(validationContext);
        }
        return validationContext.getValidateResult();
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        Student student = new Student();
        System.out.println(MyValidator.validate(student));
    }

}
