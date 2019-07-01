package com.ryml.util;

import org.apache.tomcat.util.threads.TaskThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/29
 */
public class MyThreadPoolExectuor extends ThreadPoolExecutor{

    /**
     * Record the number of tasks committed for execution
     */
    private final AtomicInteger submittedTasksCount = new AtomicInteger();

    MyThreadPoolExectuor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,TaskThreadFactory taskThreadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, taskThreadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        submittedTasksCount.incrementAndGet();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        submittedTasksCount.decrementAndGet();
    }

    /**
     * get the number of tasks committed for execution
     * @return
     */
    public Integer getSubmittedTasksCount(){
        return submittedTasksCount.intValue();
    }

}

