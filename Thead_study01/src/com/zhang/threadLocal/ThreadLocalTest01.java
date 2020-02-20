package com.zhang.threadLocal;
/**
 * ThreadLocal:每个线程自身的存储本地、局部区域、互不干扰
 *  ————————————
 * |  线程1 ：独立的工作空间|
 * |  线程2 ：独立的工作空间|
 * |  线程3 ：独立的工作空间|
 * |  线程4 ：独立的工作空间|
 *  ————————————
 * 内部方法get：获得线程块的初始值
 *       /set：修改线程块的初始值
 *       /initialValue：初始化线程块的初始值
 */
public class ThreadLocalTest01 {
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
        }).start();
        //在main线程中改变不会影响Thread线程
        threadLocal.set(10);
        System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());

        new Thread(new MyRun()).start();
        new Thread(new MyRun()).start();
    }
    public static  class MyRun implements Runnable{
        public void run() {
            threadLocal.set((int)(Math.random()*99));
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
        }
    }
}
