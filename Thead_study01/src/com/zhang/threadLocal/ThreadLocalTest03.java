package com.zhang.threadLocal;
/**
 * ThreadLocal:分析上下文 环境  起点
 * 1、构造器: 哪里调用 就属于哪里 找线程体
 * 2、run方法:本线程自身的
 */
public class ThreadLocalTest03 {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->1);

    public static void main(String[] args) {
        new Thread(new Test()).start();
        /*
        输出结果
            main-->100      因为new Test是在main方法中执行的所以会执行Test的构造方法，也就是说new Test属于main线程
            Thread-0-->50   执行run是在Thread-0中执行
         */
    }

    static class Test implements Runnable{
        public Test() {
            threadLocal.set(100);
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
        }

        @Override
        public void run() {
            threadLocal.set(50);
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
        }
    }
}
