package com.zhang;

/**
 * 模拟并发抢票
 */
public class Web99 implements Runnable {
    private int num = 99;

    @Override
    public void run() {
        while (true){
            if(num<0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ num--);
        }
    }

    public static void main(String[] args) {
        Web99 web99 = new Web99();
        new Thread(web99,"sp1").start();
        new Thread(web99,"sp2").start();
        new Thread(web99,"sp3").start();
    }
}
