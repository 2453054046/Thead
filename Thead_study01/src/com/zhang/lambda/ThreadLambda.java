package com.zhang.lambda;

/**
 * lamdba表达式，
 * 推导：
 * 简化线程(用一次)的使用
 * 当类只有一个方法时使用，
 */
public class ThreadLambda {
    //静态内部类
    static class Test implements Runnable{
        @Override
        public void run() {
            System.out.println("静态内部类");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test).start();

        //局部内部类
        class Test2 implements Runnable{
            @Override
            public void run() {
                System.out.println("局部内部类");
            }
        }
        Test2 test2 = new Test2();
        new Thread(test2).start();

        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        }).start();

        //jdk8  简化使用  lambda表达式
        new Thread(()->{
            System.out.println("lamdba表达式");
        }).start();
    }
}