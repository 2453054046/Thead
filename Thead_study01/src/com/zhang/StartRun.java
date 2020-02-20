package com.zhang;

/**
 * 创建线程方式二：
 * 创建：实现Runnable类重写run
 * 启动：创建Thread类传入子类的对象.start
 */
public class StartRun implements Runnable {
    /**
     * 线程入口点
     * run方法不允许抛出异常，只能捕获
     */
    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            System.out.println("一边听歌");
        }
    }
    public static void main(String[] args) {
        //创建子类对象
        StartRun st =new StartRun();
        //启动
        //创建Thread对象传入线程子类对象
        new Thread(st).start();//不保证立即运行 cpu调用

        //st.run(); //普通方法调用
        for(int i=0;i<20;i++) {
            System.out.println("一边coding");
        }
    }
}
