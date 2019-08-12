package com.ryml.entity;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/7
 */
public class Dog {

    private Integer id;

    private String name;

    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (id != null ? !id.equals(dog.id) : dog.id != null) return false;
        if (name != null ? !name.equals(dog.name) : dog.name != null) return false;
        return age != null ? age.equals(dog.age) : dog.age == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
