package com.ryml.demo.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/22
 */
public class TestLockDemo {

    public static void main(String[] args) throws InterruptedException {
        //Codition典型用例 生产者消费者模式
        ConditionTest conditionTest = new ConditionTest(30);
        conditionTest.set("a");
        System.out.println(conditionTest.get());
    }

}
class ConditionTest{
    /**
     * 容器
     */
    private LinkedList<String> buffer;
    /**
     * 容器最大值
     */
    private int maxSize ;
    private Lock lock;
    private Condition fullCondition;
    private Condition notFullCondition;

    ConditionTest(int maxSize){
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void set(String string) throws InterruptedException {
        lock.lock();    //获取锁
        try {
            while (maxSize == buffer.size()){
                notFullCondition.await();       //满了，添加的线程进入等待状态
            }

            buffer.add(string);
            fullCondition.signal();
        } finally {
            lock.unlock();      //记得释放锁
        }
    }

    public String get() throws InterruptedException {
        String string;
        lock.lock();
        try {
            while (buffer.size() == 0){
                fullCondition.await();
            }
            string = buffer.poll();
            notFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return string;
    }
}
