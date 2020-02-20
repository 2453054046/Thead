package com.zhang.block;

/**
 * join需要Thread对象才能执行
 * 线程插队，合并线程
 * 被插队的线程需要等插队线程执行完毕才能继续执行
 */
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new father()).start();
        Thread thread = new Thread(()->{
            for (int i=0;i<100;i++){
                System.out.println("Thread线程..."+i);
            }
        });
        thread.start();

        for (int i=0;i<100;i++){
            System.out.println("main线程..."+i);
            if(i%20 == 0){
                //线程被插队了
                thread.join();
            }
        }
    }
}

/**
 * 父子买烟
 */
class father implements Runnable{
    @Override
    public void run() {
        System.out.println("父亲抽烟，没烟了让儿子买烟");
        Thread thread = new Thread(new Son());
        //线程插队
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("儿子跑丢了");
        }

        System.out.println("父亲拿到烟开始抽。。。。");
    }
}

class Son implements Runnable{
    @Override
    public void run() {
        System.out.println("开始买烟\n进入游戏厅");
        for(int i=0;i<10;i++){
            System.out.println("玩了"+i+"秒");
        }
        System.out.println("去买烟");
    }
}