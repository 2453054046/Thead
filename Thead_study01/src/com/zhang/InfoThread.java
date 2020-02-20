package com.zhang;

/**
 * isAlive
 * 判断线程是否还活着
 */
public class InfoThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new info("info线程"));
        //设置线程的代理名字
        thread.setName("zl");
        thread.start();
        //判断
        System.out.println(thread.isAlive());
        Thread.sleep(200);
        System.out.println(thread.isAlive());

    }
}
class info implements Runnable{
    private String name;
    info(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" -->"+ name);
        }
    }
}