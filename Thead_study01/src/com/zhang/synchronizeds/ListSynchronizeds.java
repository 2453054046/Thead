package com.zhang.synchronizeds;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合自带线程锁
 */
public class ListSynchronizeds {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        for(int i=0;i<1000;i++){
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        //让线程睡500毫秒让循环线程执行完毕
        Thread.sleep(500);
        System.out.println(list.size());
    }
}
