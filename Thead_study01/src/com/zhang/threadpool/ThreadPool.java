package com.zhang.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 原理： 创建数组集合或者链表集合，然后在集合中创建线程，每次使用线程remove出一个线程，使用完再add回去
 * JDK1.5以后Executors线程池类，newFixedThreadPool静态方法创建参数对应的线程放在线程池中
 * submit:从线程池中获得一个线程，运行对应的线程实现类
 *
 */
public class ThreadPool {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //取出线程池中一个线程，使用start方法运行参数类的run方法
        executorService.submit(new RunnableImpl());
        //结束线程池
        executorService.shutdown();
    }
}
class RunnableImpl implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}