package com.zhang.lock;

/**
 * 可重入锁: 锁可以延续使用 + 计数器
 LockTest04是ReentrantLock类的实现原理
 */
public class ReentrantLock {
    java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
    public void a() throws InterruptedException {
        lock.lock();
        doSomething();
        lock.unlock();
    }
    //不可重入
    public void doSomething() throws InterruptedException {
        lock.lock();
        System.out.println(lock.getHoldCount());
        //...................
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock test = new ReentrantLock();
        test.a();
        Thread.sleep(1000);
        System.out.println(test.lock.getHoldCount());
    }
}
