package com.ryml.entity;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/7
 */
public class Cat {
    private Integer id;

    private String name;

    private Integer age;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (id != null ? !id.equals(cat.id) : cat.id != null) return false;
        if (name != null ? !name.equals(cat.name) : cat.name != null) return false;
        return age != null ? age.equals(cat.age) : cat.age == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Dog dog1 = new Dog();
        System.out.println(cat.hashCode());
        System.out.println(dog.hashCode());
        System.out.println(dog1.hashCode());
        System.out.println(dog1.equals(dog));
    }
}
