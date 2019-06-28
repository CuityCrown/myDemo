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
public class MyValidator {

    public static <T>  ValidateResult validate(T object) throws IllegalAccessException, ClassNotFoundException {
        if (object == null){
            throw new RuntimeException("object can not be null");
        }
        ValidationContext validationContext = new ValidationContext(object);

        Validator validator = new FieldsValidator();
        //初始化校验前所需要的参数
        validator.initValidationContext(validationContext);

        //执行校验
        validator.volidate(validationContext);

        return validationContext.getValidateResult();
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        Student student = new Student();
        System.out.println(MyValidator.validate(student).getMessages().toString());
    }

}
