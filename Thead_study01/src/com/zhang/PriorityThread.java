package com.zhang;

/**
 * 线程优先级
 * NORM_PRIORITY 5   默认
 * MAX_PRIORITY  10
 * MIN_PRIORITY  1
 * 只是cpu概率比较大，并不是绝对调用
 */
public class PriorityThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread);
        Thread thread3 = new Thread(myThread);
        Thread thread4 = new Thread(myThread);
        Thread thread5 = new Thread(myThread);
        Thread thread6 = new Thread(myThread);
        //设置优先级最大
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.NORM_PRIORITY);
        //设置优先级最小
        thread4.setPriority(Thread.NORM_PRIORITY+1); //当前设置线程优先级加一
        thread5.setPriority(Thread.MIN_PRIORITY);
        thread6.setPriority(Thread.MIN_PRIORITY);
        //启动线程
        thread4.start();
        thread5.start();
        thread6.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
class MyThread implements Runnable{

    @Override
    public void run() {
        //Thread.currentThread().getPriority():获得当前线程的优先级别
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }
}