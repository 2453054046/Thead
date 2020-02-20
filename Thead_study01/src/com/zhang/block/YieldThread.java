package com.zhang.block;

/**
 * yield:线程礼让，进入就绪状态（不占用资源）
 * 在那个线程中调用那个线程开始礼让
 * 但是有可能不会礼让成功，因为cpu下次还是调用本线程
 */
public class YieldThread {
    public static void main(String[] args) {
        new Thread(()->{
            for (int i=0;i<100;i++){
                System.out.println("Thread线程..."+i);
            }
        }).start();
        for (int i=0;i<100;i++){
            System.out.println("main线程..."+i);
            if(i%20 == 0){
                Thread.yield();
            }
        }

    }
}
