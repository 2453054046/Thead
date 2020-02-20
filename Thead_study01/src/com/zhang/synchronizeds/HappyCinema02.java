package com.zhang.synchronizeds;

/**
 * 火车票购票选座（改进电影院购票）
 */
public class HappyCinema02 {
    public static void main(String[] args) {
        Web12306 c = new Web12306(4,"happy sxt");
        new  Passenger(c,"老高",2).start();
        new  Passenger(c,"老裴",1).start();

    }
}
//顾客
class Passenger extends Thread{
    //订票网站
    Web12306 cinema;
    //选取位置
    int seats;
    Passenger(Web12306 cinema,String name,int seats){
        super(cinema,name);
        this.seats = seats;
    }


}

//订票网站
class Web12306 implements Runnable{
    //可用位置
    int available;
    //影院名
    String name;

    public Web12306(int available, String name) {
        this.available = available;
        this.name = name;
    }
    //购票
    public boolean bookTickets(int seats){
        System.out.println("可用位置为:"+available);
        if(seats>available) {
            return false;
        }
        available -=seats;
        return true;
    }
    @Override
    public synchronized void run() {
        Passenger passenger = (Passenger) Thread.currentThread();
        boolean b = this.bookTickets(passenger.seats);
            if (b) {
                System.out.println("出票成功" + Thread.currentThread().getName() + "-<位置为:" + passenger.seats);
            } else {
                System.out.println("出票失败" + Thread.currentThread().getName() + "-<位置不够");
            }

    }
}
