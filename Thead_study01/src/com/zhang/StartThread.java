package com.zhang;

/**
 * 创建线程方式一：
 * 创建：继承Thread类重写run
 * 启动：创建子类的对象.start
 */
public class StartThread extends Thread {
    /**
     * 线程入口点
     */
    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            System.out.println("一边听歌");
        }
    }
    public static void main(String[] args) {
        //创建子类对象
        StartThread st =new StartThread();
        //启动
        st.start(); //不保证立即运行 cpu调用
        //st.run(); //普通方法调用
        for(int i=0;i<20;i++) {
            System.out.println("一边coding");
        }
    }
}
