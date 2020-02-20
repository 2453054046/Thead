package com.zhang;

import javax.management.monitor.Monitor;

/**
 * 查看线程的状态
 */
public class AllState {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for(int i=0; i<5; i++){
                System.out.println("线程启动时间："+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //获得线程的状态
        Thread.State state = thread.getState();
        System.out.println("线程new"+state);//NEW  创建了线程，进入就绪

        thread.start();

        state = thread.getState();
        System.out.println("线程启动"+state);//RUNNABLE    线程开始运行

        //监视线程的状态
        while (true){
            //获得当前正在运行的所有线程名
            ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
            int noThreads = currentGroup.activeCount();
            Thread[] lstThreads = new Thread[noThreads];
            currentGroup.enumerate(lstThreads);
            for (int i = 0; i < noThreads; i++)
                System.out.println("线程号：" + i + " = " + lstThreads[i].getName());



            //获得目前有多少线程在执行中
            int i = Thread.activeCount();
            System.out.println("线程的数量是："+i);
            //如果目前只有主线程main在执行（也就是thread执行完毕，只有当前while循环在执行）
            // 时跳出循环结束监视，
            // 但是有一个线程不是main主线程但是会一直执行
            /**
             * Monitor Ctrl-Break线程：目前未知线程
             * 了解地址：https://docs.oracle.com/cd/E13188_01/jrockit/docs50/userguide/apstkdmp.html
             */
            if(i==2){
                break;
            }
            //每过两毫秒监视一次
            try {
                Thread.sleep(200);//TIMED_WAITING  线程进入阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            state = thread.getState();
            System.out.println(state);
        }

    }
}
