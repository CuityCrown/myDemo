package com.ryml.util;

import java.util.ArrayList;
import java.util.List;

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
    private ValidateResult validateResult = new ValidateResult();

    private List<Class<?>> classList = new ArrayList<>();

    /**
     * 需要校验的对象
     */
    private Object object;

    public ValidateResult getValidateResult() {
        return validateResult;
    }

    public void setValidateResult(ValidateResult validateResult) {
        this.validateResult = validateResult;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void addClassList(Class<?> aClass){
        this.classList.add(aClass);
    }

    public List<Class<?>> getClassList() {
        return classList;
    }
}
