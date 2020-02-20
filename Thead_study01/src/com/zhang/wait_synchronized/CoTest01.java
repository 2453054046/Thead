package com.zhang.wait_synchronized;

import org.junit.Test;

/**
 * wait：阻塞线程，但是释放线程锁不占用线程锁，需要唤醒才能继续执行
 * notifyAll：
 * 管程法唤醒所有wait睡眠线程
 * 线程生产者和消费者模式
 */
public class CoTest01 {
    public static void main(String[] args) {

        SynContainer synContainer = new SynContainer();
        new Thread(new Productor(synContainer)).start();
        new Thread(new Consumer(synContainer)).start();

        /* 测试虚假唤醒
        new Thread(new Productor(synContainer)).start();
        new Thread(new Consumer(synContainer)).start();*/
    }

}

//生产者
class Productor extends Thread{
    SynContainer synContainer;

    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            synContainer.push(new Steamedbun(i));
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer synContainer;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("消费-->"+synContainer.pop().id+"个馒头");
        }
    }
}

//缓冲区
class SynContainer{
    Steamedbun[] steamedbun = new Steamedbun[10];       //数据存储容器
    int count=0;        //计数器，取出容器中对应下标的数据
    //生产
    public synchronized void push(Steamedbun data){
        //容器空间满了不能生产，阻塞
        while(count == steamedbun.length){
            try {
                /**
                 * this.wait:中断和虚假唤醒是可能的，并且该方法应该始终在循环中使用
                 * 比如：生产者和消费者有多个，那么有可能出现 消费负数
                 * */
                this.wait();
                System.out.println("生产者阻塞");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        steamedbun[count] = data;
        count++;
        System.out.println("唤醒消费者--");
        this.notifyAll(); //生产出数据了，唤醒消费
    }
    //消费
    public synchronized Steamedbun pop(){
        //没有数据时等待生产者生产，阻塞
        while (count==0){
            try {
                this.wait();
                System.out.println("消费者阻塞");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Steamedbun bun = steamedbun[count] ;
        System.out.println("准备唤醒生产");
        this.notifyAll();  //有存储空间了，唤醒生产
        return bun;
    }
}
//数据
class Steamedbun{
    int id; //记录当前的数据是第一个
    public Steamedbun(int id) {
        this.id=id;
    }
}