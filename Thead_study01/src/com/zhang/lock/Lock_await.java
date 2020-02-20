package com.zhang.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock锁实现线程生产者和消费者模式
 *
 * Lock.lock()：上锁
 * lock.unlock(): 释放锁  需要放在try{}finally{}中
 *
 * Condition类：它用来替代传统的Object的wait()、notify()实现线程间的协作，
 *              相比使用Object的wait()、notify()，使用Condition的await()、
 *              signal()这种方式实现线程间协作更加安全和高效。因此通常来说比较推荐使用Condition，
 *              阻塞队列实际上是使用了Condition来模拟线程间协作。
 * Condition.await():相当于 Object.wait() 让线程等待
 * Condition.signal() : 相当于Object.notify() 唤醒一个等待线程
 * Condition.signalAll() : 相当于Object.notifyAll() 唤醒所有等待线程
 *
 *
 */
public class Lock_await {
    public static void main(String[] args) {

        SynContainer synContainer = new SynContainer();
        new Thread(new Productor(synContainer)).start();
        new Thread(new Consumer(synContainer)).start();

        /* 测试虚假唤醒
        new Thread(new Productor(synContainer)).start();
        new Thread(new Consumer(synContainer)).start();*/
    }

}

//生产者
class Productor extends Thread{
    SynContainer synContainer;

    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            synContainer.push(new Steamedbun(i));
        }
    }
}

//消费者
class Consumer extends Thread{
    SynContainer synContainer;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("消费-->"+synContainer.pop().id+"个馒头");
        }
    }
}

//缓冲区
class SynContainer{
    Steamedbun[] steamedbun = new Steamedbun[10];       //数据存储容器
    int count=0;        //计数器，取出容器中对应下标的数据

    //创建Lock锁
    private Lock lock = new ReentrantLock();

    //创建Condition对象，代替Object的wait()、notify()
    private Condition condition = lock.newCondition();

    //生产
    public void push(Steamedbun data){
        //获得锁
        lock.lock();

        try {
            //容器空间满了不能生产，阻塞
            while(count == steamedbun.length){
                try {
                    /**
                     * this.wait:中断和虚假唤醒是可能的，并且该方法应该始终在循环中使用
                     * 比如：生产者和消费者有多个，那么有可能出现 消费负数
                     * */
                    condition.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            steamedbun[count] = data;
            count++;
            //唤醒消费者
            condition.signalAll();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
    //消费
    public Steamedbun pop(){
        //获得锁
        lock.lock();
        Steamedbun bun;
        try {
            //没有数据时等待生产者生产，阻塞
            while (count==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            bun = steamedbun[count];
            condition.signalAll();  //有存储空间了，唤醒生产
            return bun;
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}

//数据
class Steamedbun{
    int id; //记录当前的数据是第一个
    public Steamedbun(int id) {
        this.id=id;
    }
}