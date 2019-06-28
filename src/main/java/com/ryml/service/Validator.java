package com.ryml.service;

import com.ryml.util.ValidationContext;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
public interface Validator {

    /**
     * init validationContext before validate
     * @param object
     * @throws ClassNotFoundException
     */
    <T> ValidationContext initValidationContext(T object) throws ClassNotFoundException;

    /**
     *
     * @param validationContext
     * @throws IllegalAccessException
     */
    void validate(ValidationContext validationContext) throws IllegalAccessException;

}
