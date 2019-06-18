package com.ryml.enums;

/**
 * description:redis key公共枚举
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/18
 */
public enum RedisCommonEnum {
    STUDENT("学生key值","1001");

    private String name;

    private String value;

    RedisCommonEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
