package com.zhang.读写锁;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读和读：不需要互斥
 * 读和写 && 写和写 ：许哟啊互斥
 */
public class MyReadWriteLock {
    public static void main(String[] args) {
        ReadWriteDemo rw = new ReadWriteDemo();
        new Thread(() -> rw.set(18)).start();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> rw.get()).start();
        }
    }
}

class ReadWriteDemo {
    //创建读写锁
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //共享数据
    int num = 0;

    //读
    public void get() {
        //获得读数据锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t:" + num);
        } finally {
            //释放读数据锁
            lock.readLock().unlock();
        }
    }

    //写数据
    public void set(int num) {
        //获得写数据锁
        lock.writeLock().lock();
        try {
            System.out.println("Write ---> " + num);
            this.num = num;
        } finally {
            //释放写数据锁
            lock.writeLock().unlock();
        }

    }
}
