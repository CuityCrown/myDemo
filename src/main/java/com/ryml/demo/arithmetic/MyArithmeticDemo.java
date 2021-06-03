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
        int[] s = new int[]{9,3,2,1,6};
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
        for (int i : s) {
            System.out.println(i);
        }
        System.out.println("===============");
        executeK(s,left,left1-1);
        executeK(s,left1+1,right);
    }

    @Test
    public void test4(){
        Node node = new Node(1,null);
        Node node1 = new Node(2, null);
        node.setNext(node1);
        Node node2 = new Node(3, null);
        node1.setNext(node2);
        Node node3 = new Node(4, null);
        node2.setNext(node3);
        Node node4 = new Node(5, null);
        node3.setNext(node4);
        Node node5 = new Node( 6, null);
        node4.setNext(node5);

        Node node6 = testF(node);
        System.out.println("---------------------");
        Node node7 = node6;
        while(node7 != null){
            System.out.println(node7.getItem());
            node7 = node7.getNext();
        }
    }

    public Node testF(Node node){
        Node node1 = node;
        Node node2 = node.getNext();
        Node node3;
        while(node2 != null){
          node3 = node2.next;
          node2.next = node1;
          node1 = node2;
          node2 = node3;
        }
        node.next = null;
        return node1;
    }


    public static class Node<E> {
        E item;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

    }


}