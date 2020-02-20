package com.zhang.block;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * sleep：线程睡眠（资源占用）
 * 倒计时
 */
public class SleepThread {
    public static void main(String[] args) throws InterruptedException {
        //到数十个数     System.currentTimeMillis:返回当前计算机系统的时间
        Date date = new Date(System.currentTimeMillis());
        //getTime()获得当前时间的毫秒数
        Long end = date.getTime();
        System.out.println(date);
        while (true){
            Thread.sleep(1000);
            //格式化打印当前时间
            System.out.println(new SimpleDateFormat("mm:ss").format(date));
            //时间减去一秒
            date = new Date(date.getTime()-1000);
            if(end-10000>date.getTime()){
                break;
            }
        }

    }
}
