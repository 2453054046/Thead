package com.zhang.lambda;

public class LamdbaTest02 {
    public static void main(String[] args) {
        //正常使用
        Ilove ilove = new IloveImpl();
        ilove.lamdba(10);

        //临时扩展或修改Ilove类，简化1
        ilove = (a)->{
            System.out.println("临时扩展Ilove类  参数是："+a);
        };
        ilove.lamdba(11);

        //简化2
        ilove = a->{
            System.out.println("临时扩展Ilove类  参数是："+a);
        };
        ilove.lamdba(12);
        //简化3
        ilove = a-> System.out.println("临时扩展Ilove类  参数是："+a);
        ilove.lamdba(13);
    }
}

interface Ilove{
    void lamdba(int a);
}

class IloveImpl implements Ilove{
    @Override
    public void lamdba(int a) {
        System.out.println("参数是："+a);
    }
}