package com.zhang.volatiles;

/**
 * volatile用于保证数据的同步，也就是可见性
 * 用于当主内存调用时反应不过来数据已更改（主内存过于忙碌）来同步数据
 * volatile是轻量级的synchronized，它只保证数据的安全，
 * volatile会排除指令重排
 */
public class VolatileTest {
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num == 0){
                //什么都不要写，造成主内存过于忙碌
            }
        });
        //更改num
        Thread.sleep(1000);
        num=1;
        //系统速度过快测试失败

    }
}
