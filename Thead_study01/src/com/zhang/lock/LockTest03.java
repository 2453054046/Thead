package com.zhang.lock;

/**
 * 不可重入锁原理：锁不可以延续使用
 */
public class LockTest03 {
    Lock02 lock02 = new Lock02();
    public void a() throws InterruptedException {
        lock02.lock();
        doSomething();
        lock02.unlock();
    }
    //第二次访问
    public void doSomething() throws InterruptedException {
        lock02.lock();
        lock02.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest03 lockTest03 = new LockTest03();
        lockTest03.a();
        lockTest03.doSomething();
    }

}
class Lock02{
    //第一次访问时是false，更改为true
    private boolean isLocked = false;
    //记录线程锁的个数
    //private int holdCount = 0;

    //获得锁
    //让线程第二次访问时睡眠
    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            //System.out.println("线程随眠");
            this.wait();
        }
        //更改标记为true,让第二次访问导致线程睡眠
        isLocked = true;
        //holdCount++;  //线程锁加一
    }

    //释放锁
    public void unlock(){
        //更改标记为false，表示当前锁已被释放
        isLocked = false;
        notify();
    }
}
