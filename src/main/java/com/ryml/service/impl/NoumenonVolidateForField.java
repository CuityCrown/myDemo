package com.ryml.service.impl;

import com.ryml.service.MyVolidateForFieldActuator;
import com.ryml.util.ValidateResult;
import com.ryml.util.ValidationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
@Service
public class NoumenonVolidateForField implements MyVolidateForFieldActuator {

    @Override
    public void validateProperties(ValidationContext validationContext) throws IllegalAccessException {
        Object object = validationContext.getObject();
        ValidateAnnotationActuator instance = ValidateAnnotationActuator.getInstance();
        validationContext.addClassList(object.getClass());
        instance.validateProperties(validationContext);
    }

}
