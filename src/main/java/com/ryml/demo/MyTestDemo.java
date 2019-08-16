package com.ryml.demo;

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
               if (j != 1 && i != j && i%j == 0){
                   count++;
               }
            }
            if (count == 0){
                System.out.println(i);
            }
            count = 0;
        }

    }
}

class Single{

    private static Single single = new Single();

    public static Single getSingle(){
        return single;
    }

}

class Single2{

    private static Single2 single2;

    public static Single2 getSingle2(){

        if (single2 == null){
            synchronized (single2){
                if (single2 == null){
                    single2 = new Single2();
                }
            }
        }
        return single2;
    }
}

class Single3{

    private static class Single{
        private static Single3 single3 = new Single3();
    }

    public static Single3 getSingle3(){
        return Single.single3;
    }

    protected Object readResolve() {
        return Single.single3;
	}
}
