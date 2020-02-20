package com.zhang.synchronizeds;

import com.zhang.Web99;

/**
 * 线程锁
 * 模拟并发抢票
 * synchronized:放在方法上，锁this当前对象所有操作
 *
 */
public class synchronizedThread99 {
    public static void main(String[] args) {
        SynWeb12306 web99 = new SynWeb12306();
        new Thread(web99,"sp1").start();
        new Thread(web99,"sp2").start();
        new Thread(web99,"sp3").start();
    }
}
class SynWeb12306 implements Runnable{
    private int num = 50;
    Boolean flag = true;
    @Override
    public void run() {
        while (flag){
            test02();
        }
    }
    //同步块
    //线程安全:尽可能锁定合理的范围(不是指代码 指数据的完整性)
    //double checking
    public synchronized void test02(){
        if(num<=0){     //考虑的是没有票的情况
            flag=false;
            return;
        }
        synchronized (this){
            if(num<=0){     //考虑最后的1张票
                flag=false;
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ num--);
        }
    }
    //同步锁方法
    public synchronized void test(){
        if(num<=0){
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            flag=false;
            return;
        }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(Thread.currentThread().getName()+"-->"+ num--);
    }
}
