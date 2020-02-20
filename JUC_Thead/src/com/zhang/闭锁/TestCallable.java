package com.zhang.闭锁;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程三：
 * 1、实现Callable接口，相较于实现Runnable接口的方式，方法可以有返回值，并且可以抛出异常
 * 2、执行Callable方式，需要FutureTask实现类的支持，用于接收运算的返回值，FutureTask时Future接口的实现类
 *
 *
 */
public class TestCallable {

    public static void main(String[] args) {

        CallableDemo callableDemo = new CallableDemo();
        /**
         * new FutureTask(Callable的实现类)
         * FutureTask可用于闭锁。
         */
        FutureTask<Integer> integerFutureTask = new FutureTask<>(callableDemo);
        new Thread(integerFutureTask).start();
        try {

            System.out.println(integerFutureTask.get());
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new MyThread2()).start();

    }
}
class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        //计算1-100的总和返回
        for (int i = 0; i <= 100 ; i++) {
            sum+=i;
        }
        return sum;
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}