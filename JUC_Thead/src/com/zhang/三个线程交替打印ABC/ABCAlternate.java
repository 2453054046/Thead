package com.zhang.三个线程交替打印ABC;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCAlternate {
    public static void main(String[] args) {
        Alternate alternate = new Alternate();
        new Thread(()->{

                alternate.loopA();


        },"A").start();
        new Thread(()->{

                alternate.loopB();

        },"B").start();
        new Thread(()->{

                alternate.loopC();

        },"C").start();
    }
}
class Alternate{

    /**
     * 证明：wait后面的代码不会继续执行，直到被唤醒
     */
    int anInt = 2;

    private Lock lock = new ReentrantLock();
    private Condition con1 = lock.newCondition();
    private Condition con2 = lock.newCondition();
    private Condition con3 = lock.newCondition();
    public void loopA(){
        //获得锁
        lock.lock();

        try {
            //如果当前anInt不是1就开始等待线程
            if (anInt != 1){
                con1.await();
            }
            //如果当前是1，就打印
            System.out.println(Thread.currentThread().getName());
            //打印后将anInt更改为2，并唤醒其他线程
            anInt = 2;
            con2.signal();
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //释放锁
            lock.unlock();
        }
    }
    public void loopB(){
        //获得锁
        lock.lock();

        try {
            //如果当前anInt不是2就开始等待线程
            if (anInt != 2){
                con2.await();
            }
            //如果当前是2，就打印
            System.out.println(Thread.currentThread().getName());
            //打印后将anInt更改为3，并唤醒其他线程
            anInt = 3;
            con3.signal();
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //释放锁
            lock.unlock();
        }
    }
    public void loopC(){
        //获得锁
        lock.lock();

        try {
            //如果当前anInt不是3就开始等待线程
            if (anInt != 3){
                con3.await();
            }
            //如果当前是3，就打印
            System.out.println(Thread.currentThread().getName());
            //打印后将anInt更改为1，并唤醒其他线程
            anInt = 1;
            con1.signal();
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //释放锁
            lock.unlock();
        }
    }
}
