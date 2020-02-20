package com.zhang;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度: Timer 和TimerTask类
 * TimerTask:继承Runnable
 */
public class TimerTest01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        /**
         * Timer.schedule:启动线程程序
         * Timer.schedule(启动的对象，启动的时间，每隔多长时间再次启动)
         */
        timer.schedule(new MyTask(), 1000);  //执行任务一次
        timer.schedule(new MyTask(), 1000,200); //执行多次
        //Calendar日历类  GregorianCalendar继承Calendar，设置日期时间
        Calendar cal = new GregorianCalendar(2099999,12,31,21,53,54);
        timer.schedule(new MyTask(), cal.getTime(),200);
    }
}
//任务类
class  MyTask extends TimerTask {

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.println("放空大脑休息一会");
        }
        System.out.println("------end-------");
    }
}