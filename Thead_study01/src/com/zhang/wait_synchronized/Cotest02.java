package com.zhang.wait_synchronized;

import java.util.TreeMap;

/**
 * wait：阻塞线程，但是释放线程锁不占用线程锁，需要唤醒才能继续执行
 * notifyAll：唤醒所有wait睡眠线程
 * 红灯法
 * 线程生产者和消费者模式
 */
public class Cotest02 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Watcher(tv).start();
        new Player(tv).start();
    }
}
//同一个资源 电视
class Tv{
    String data;
    //信号灯
    //T 表示演员表演 观众等待
    //F 表示观众观看 演员等待
    boolean flag = true;
    //演员表演
    public synchronized void play(String data) {
        //演员等待
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员开始表演。。。。"+data);
        this.data = data;
        //唤醒观众开始观看
        this.notifyAll();
        //切换信号灯
        this.flag=!this.flag;
    }
    //观众观看
    public synchronized void watch() {
        //判断信号灯
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("开始观看。。。"+data);
        //唤醒演员表演
        this.notifyAll();
        //切换信号灯
        this.flag = !this.flag;
    }
}
//生产者 演员
class Player extends Thread{
    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for(int i=0; i<20;i++){
            tv.play("奇葩说");
        }

    }
}
//消费者 观众
class Watcher extends Thread{
    Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for(int i=0; i<20;i++) {
            tv.watch();
        }
    }
}