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
     * init validationContext before volidate
     * @param validationContext
     * @throws ClassNotFoundException
     */
    void initValidationContext(ValidationContext validationContext) throws ClassNotFoundException;

    /**
     * volidate
     * @param validationContext
     * @throws IllegalAccessException
     */
    void volidate(ValidationContext validationContext) throws IllegalAccessException;

}
