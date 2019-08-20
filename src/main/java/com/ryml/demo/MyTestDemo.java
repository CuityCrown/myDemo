package com.ryml.demo;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/16 19:56
 */
public class MyTestDemo {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i < 1001; i++) {
            for (int j = 1; j <= i; j++) {
                if (j != 1 && i != j && i % j == 0) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println(i);
            }
            count = 0;
        }

    }

    @Test
    public void testM(){

    }

    @Test
    public void test() {
        int j = 2;
        int o = 3;
        int d;
        for (int i = 0; i < 28; i++) {
            d = j;
            j = o;
            o = j + d;
            System.out.println(i);
        }

        System.out.println(o);
    }

    @Test
    public void testSingle() throws IOException {
        Single3 single3 = Single3.getSingle3();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(single3);
        byte[] bytes = os.toByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(is);
        Single3 single31 = (Single3) hi.readObject();

        System.out.println(single3);
        System.out.println(single31);
    }

    @Test
    public void testHashCode(){

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    }

}

class Single {

    private static Single single = new Single();

    public static Single getSingle() {
        return single;
    }

}

class Single2 {

    private static Single2 single2;

    public static Single2 getSingle2() {

        if (single2 == null) {
            synchronized (single2) {
                if (single2 == null) {
                    single2 = new Single2();
                }
            }
        }
        return single2;
    }
}

class Single3 implements Serializable {

    private static class Single {
        private static Single3 single3 = new Single3();
    }

    public static Single3 getSingle3() {
        return Single.single3;
    }

    protected Object readResolve() {
        return Single.single3;
    }
}

