package com.zhang;

/**
 * 模拟龟兔赛跑
 */
public class Race implements Runnable{
    private String winner;
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            /*
                模拟兔子睡觉
             */
            if(Thread.currentThread().getName().equals("tortoise") && i%10==0){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Thread.currentThread.getName()：显示当前线程的名字
            System.out.println(Thread.currentThread().getName()+">"+i);
            /*if(winner!=null){
                break;
            }else {
                if(i==100){
                    winner = Thread.currentThread().getName();
                    System.out.println("winner：-->"+winner);
                }
            }*/
            boolean flag = gameOver(i);
            if(flag) {
                break;
            }
        }
    }
    private Boolean gameOver(int i){
        if(winner!=null){
            return true;
        }else {
            if(i==100){
                winner = Thread.currentThread().getName();
                System.out.println("winner：-->"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        //启动线程
        //构造方法 Thread(执行的线程类，线程名)
        new Thread(race,"tortoise").start();
        new Thread(race,"rabbit").start();
    }
}
