package com.zhang.synchronizeds;

import java.util.Date;
import java.util.TreeMap;

/**
 * 转账
 * synchronized：
 * 放在方法上同步（锁）this当前对象，
 * 使用同步块（锁）
 *      synchronized（要锁的对象）
 *      注意：同步块需要考虑锁的范围，太小锁不住（锁错位置）
 */
public class synchronizedThread02 {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚礼金");
        Drawing my = new Drawing(account, 80, "本人");
        Drawing you = new Drawing(account, 90, "别人");
        my.start();
        you.start();
    }

}

//账户
class Account{
    int money; //金额
    String name; //名称
    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}
//取款机
class Drawing extends Thread{
    Account account ; //取钱的账户
    int drawingMoney ;//取的钱数
    int packetTotal ; //取钱的总数

    //构造方法设置操作数据
    public Drawing(Account account, int drawingMoney ,String name) {
        //设置当前线程的代理名称
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        //提高性能(重点需注意)
        //双重检测
        if(account.money - drawingMoney < 0){
            return;
        }
        //同步块
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                return;
            }
            //模拟网络延迟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawingMoney;
            packetTotal += drawingMoney;
            System.out.println(this.getName() + "-->当前账户余额是：" + account.money);
            System.out.println(this.getName() + "-->当前取出的总数是：" + packetTotal);
        }
    }

}

