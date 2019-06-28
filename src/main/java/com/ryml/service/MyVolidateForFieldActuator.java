package com.ryml.service;

import com.ryml.util.ValidationContext;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
public interface MyVolidateForFieldActuator {

    void validateProperties(ValidationContext validationContext) throws ClassNotFoundException, IllegalAccessException;

}
