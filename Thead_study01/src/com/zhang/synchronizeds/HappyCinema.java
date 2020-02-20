package com.zhang.synchronizeds;

import java.util.ArrayList;
import java.util.List;

/**
 * 影院购票选座
 */
public class HappyCinema {
    public static void main(String[] args) {
        List<Integer> available = new ArrayList<>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(6);
        available.add(7);
        List<Integer> seats1 = new ArrayList<Integer>();
        seats1.add(1);
        seats1.add(2);
        List<Integer> seats2 = new ArrayList<Integer>();
        seats2.add(3);
        seats2.add(6);
        Cinema cinema = new Cinema(available, "kaixin");
        new Thread(new Customer(cinema,seats2),"线程二").start();
        new Thread(new Customer(cinema,seats1),"线程一").start();

    }
}
//顾客
class Customer implements Runnable{
    //去的影院
    Cinema cinema;
    //选取位置
    List<Integer> seats;

    public Customer(Cinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }
    @Override
    public void run() {
        synchronized (cinema) {
            boolean b = cinema.bookTickets(seats);
            if (b) {
                System.out.println("出票成功" + Thread.currentThread().getName() + "-<位置为:" + seats);
            } else {
                System.out.println("出票失败" + Thread.currentThread().getName() + "-<位置不够");
            }
        }
    }
}

//影院
class Cinema{
    //可用位置
    List<Integer> available;
    //影院名
    String name;

    public Cinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }
    //购票
    public boolean bookTickets(List<Integer> seats){
        System.out.println("欢迎光临"+this.name+"，当前可用位置为:"+available);
        //拷贝当前可用位置
        System.out.println("seats是："+seats);
        List<Integer> copy = new ArrayList<>();
        copy.addAll(available);
        copy.removeAll(seats);
        if(available.size() - copy.size()!= seats.size()){
            return false;
        }
        //成功
        available = copy;
        return true;
    }
}