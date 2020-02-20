package com.zhang.lock;

/**
 * 可重入锁原理：锁可以延续使用 + 计数器
 */
public class LockTest04 {
    Lock03 lock03 = new Lock03();
    public void a() {

        try {
            lock03.lock();
            doSomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
        lock03.unlock();
    }
    //第二次访问
    public void doSomething()  {
        try {
            lock03.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
        lock03.unlock();
    }

    public static void main(String[] args)  {
        LockTest04 lockTest04 = new LockTest04();
        lockTest04.a();
        lockTest04.doSomething();
    }

}

class Lock03{
    //第一次访问时是false，更改为true
    private boolean isLocked = false;
    //记录线程锁的个数
    private int holdCount = 0;
    //记录上次访问线程是否是当前线程
    private Thread thread = null;

    //获得锁
    public synchronized void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        //判断是否是上次访问线程，如果不是就进入睡眠等待唤醒
        while (isLocked && thread != t){
            this.wait();
        }
        //更改标记为true,让第二次访问导致线程睡眠
        isLocked = true;
        //标记本次访问的线程,防止下次访问的还是本线程导致不可重入锁
        thread = t;
        holdCount++;  //线程锁加一
    }

    //释放锁
    public synchronized void unlock(){
        //判断是否是当前线程，如果是就释放线程
        if(thread == Thread.currentThread()){
            //计数当前线程重入锁还有几个锁
            holdCount --;
            System.out.println("hold是："+holdCount);
            //如果是最后一个锁，就释放线程
            if(holdCount==0){
                //更改标记为false，表示当前锁已被释放
                isLocked = false;
                notify();
                //将记录上次访问的线程更改为null
                thread=null;
            }
        }
    }
}
