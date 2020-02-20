package com.zhang;

import java.util.Map;

/**
 * 守护线程
 * JVM会在用户线程执行完毕才会停止，但是不会等守护线程执行完毕
 */
public class DaemonThread {
    public static void main(String[] args) {
        God god = new God();
        My my = new My();
        Thread thread = new Thread(god);
        Thread thread1 = new Thread(my);
        //设置God为守护线程
        thread.setDaemon(true);
        thread1.start();
        thread.start();

    }

}
class God implements Runnable{
    @Override
    public void run() {
        for(/*int i=1;i<500;i++*/;true;)
            System.out.println("守护线程正在执行");
    }
}

class My implements Runnable{
    @Override
    public void run() {
        for (int i =0;i<=360;i++)
            System.out.println("用户线程正在执行");
    }
}