package com.ryml.util;

import java.lang.reflect.Field;
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

    private List<Field> fields = new ArrayList<Field>();

    /**
     * 需要校验的对象
     */
    private T object;

    public ValidateResult getValidateResult() {
        return validateResult;
    }

    public T getObject() {
        return object;
    }

    public void addField(Field[] fields){
        for (Field field : fields) {
            this.fields.add(field);
        }
    }

    public ValidationContext(T object) {
        this.object = object;
    }

    public List<Field> getFields() {
        return fields;
    }
}
