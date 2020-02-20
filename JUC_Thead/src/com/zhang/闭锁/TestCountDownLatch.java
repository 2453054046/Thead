package com.zhang.闭锁;


import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch : 闭锁操作，在完成某些运算时，只有其他线程的运算全部完成，当前运算才会执行
 *
 * 例题：计算是个线程执行完毕的所用的时间
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        //Instant now = Instant.now();
        long l = System.currentTimeMillis();
        //new CountDownLatch(锁存器计数的数量)
        CountDownLatch countDownLatch = new CountDownLatch(10);
        LatchDemo latchDemo = new LatchDemo(countDownLatch);
        for (int i = 0; i <10 ; i++) {
            new Thread(latchDemo).start();
        }
        try {
            //导致当前线程等待，直到锁存器计数到零为止
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Instant now1 = Instant.now();
        long l1 = System.currentTimeMillis();
        //System.out.println(Duration.between(now,now1).toMillis());
        System.out.println(l1-l);
    }
}


class LatchDemo implements Runnable{

    //创建初始化闭锁
    private CountDownLatch count;

    public LatchDemo(CountDownLatch count) {
        this.count = count;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 10 ; i++) {
                if(i%2==0){
                    System.out.println(Thread.currentThread().getName()+"\t :  "+i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //让锁存器减一，当为0时main线程开始继续执行
            count.countDown();
        }
    }
}