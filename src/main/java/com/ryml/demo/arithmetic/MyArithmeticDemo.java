package com.ryml.demo.arithmetic;

import org.junit.Test;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/23 19:56
 */
public class MyArithmeticDemo {

    @Test
    public void testX(){
        int[] s = new int[]{12,876,1,45,7623,7654,143,745};

        for (int i = 0; i < s.length - 1; i++) {
            for (int j = i+1; j < s.length; j++) {
                int d;
                if (s[i] > s[j]){
                    d = s[i];
                    s[i] = s[j];
                    s[j] = d;
                }
            }
        }
/*        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < i; j++) {
                int d;
                if (s[j] > s[i]){
                    d = s[i];
                    s[i] = s[j];
                    s[j] = d;
                }
            }
        }*/
        for (int i : s) {
            System.out.println(i);
        }
    }

    @Test
    public void testM(){

        int[] s = new int[]{12,876,1,45,7623,7654,143,745};

        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < s.length-1; j++) {
                int d;
                if (s[j] > s[j+1]){
                    d = s[j];
                    s[j] = s[j+1];
                    s[j+1] = d;
                }
            }
        }
        for (int i : s) {
            System.out.println(i);
        }
    }

    @Test
    public void testK(){
        int[] s = new int[]{5,6,7,8,9,5,4,3,2,1};
        executeK(s,0,s.length-1);
        for (int i : s) {
            System.out.println(i);
        }
    }

    public void executeK(int[] s,int left,int right){
        if (left >= right){
            return;
        }
        int d = s[left];
        int left1 = left;
        int right1 = right;
        while(left1 < right1){
            while(left1 < right1 && s[right1] >= d)
                right1--;
            if (left1 < right1){
                s[left1++] = s[right1];
            }

            while(left1 < right1 && s[left1] < d)
                left1++;
            if (left1 < right1){
                s[right1--] = s[left1];
            }
        }
        s[left1] = d;
        executeK(s,left,left1-1);
        executeK(s,left1+1,right);
    }

}
