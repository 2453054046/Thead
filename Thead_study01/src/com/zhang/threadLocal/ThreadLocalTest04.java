package com.zhang.threadLocal;

/**
 * InheritableThreadLocal:
 *  继承ThreadLocal上下文 环境的数据 ，拷贝一份给子线程
 */
public class ThreadLocalTest04 {
    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) {
        threadLocal.set(2);
        //执行Test01空间的threadLocal
        new Thread(new Test01()).start();
        //使用InheritableThreadLocal拷贝给子线程
        new Thread(()->{
            //因为是拷贝父线程main的ThreadLocal，所以子线程可以随意更改，并不影响main线程的数据
            threadLocal.set(3);
            System.out.println(Thread.currentThread().getName()+"00-->"+threadLocal.get());
        }).start();
        System.out.println(Thread.currentThread().getName()+"main-->"+threadLocal.get());
    }
}
class Test01 implements Runnable{
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->1);
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"01-->"+threadLocal.get());
    }
}
