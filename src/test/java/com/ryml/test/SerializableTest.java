package com.ryml.test;

import org.junit.Test;

import java.io.*;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/26 11:33
 */
public class SerializableTest {

    @Test
    public void testSerializable() throws IOException {
        Teacher yyx = new Teacher(18, "yyx");
        System.out.println(yyx);
        OutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(yyx);
        System.out.println(yyx);
    }

}

class Teacher implements Serializable {

    private Integer id;

    private String name;

    public Teacher(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
