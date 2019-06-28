package com.ryml.service.impl;

import com.ryml.util.ValidationContext;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
@Service
public class ValidateAnnotationActuator {

    private volatile ValidateAnnotationActuator validateAnnotationActuator;

    public ValidateAnnotationActuator getInstance(){
        return validateAnnotationActuator;
    }

    public void validateProperties(ValidationContext validationContext) {

    }

}
