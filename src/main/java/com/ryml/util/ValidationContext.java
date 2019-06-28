package com.ryml.util;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/28
 */
public class ValidationContext<T> {

    /**
     * 校验的结果以及不通过信息
     */
    private ValidateResult validateResult;

    /**
     * 需要校验的对象
     */
    private T object;

    public ValidateResult getValidateResult() {
        return validateResult;
    }

    public void setValidateResult(ValidateResult validateResult) {
        this.validateResult = validateResult;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
