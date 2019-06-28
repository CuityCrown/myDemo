package com.ryml.util;

import com.ryml.entity.Student;
import com.ryml.service.Validator;
import com.ryml.service.impl.FieldsValidator;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/27
 */
public class MyValidatorUtils {

    public static <T> ValidateResult validate(T object) throws IllegalAccessException, ClassNotFoundException {
        if (object == null){
            throw new RuntimeException("object can not be null");

        }

        //create Validator by ElementType
        Validator validator = new FieldsValidator();

        //init validationContext
        ValidationContext validationContext = validator.initValidationContext(object);

        //execute validate
        validator.validate(validationContext);

        return validationContext.getValidateResult();
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        Student student = new Student();
        System.out.println(MyValidatorUtils.validate(student).getMessages().toString());
    }

}
