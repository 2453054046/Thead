package com.zhang.threadLocal;

public class ThreadLocalTest02 {
    //正常写法
   /* private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };*/
    //jdk8后的写法withInitial后面跟lambda格式
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->1);
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new Test()).start();
        }
    }
    static class Test implements Runnable{
        @Override
        public void run() {
            Integer integer = threadLocal.get();
            System.out.println(Thread.currentThread().getName()+"得到了-->"+integer);
            threadLocal.set(integer-1);
            System.out.println(Thread.currentThread().getName()+"还剩-->"+threadLocal.get());

        }
    }

}
