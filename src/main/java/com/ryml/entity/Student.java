package com.ryml.entity;

import java.io.Serializable;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/17
 */
public class Student implements Serializable{

    private Integer id;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
