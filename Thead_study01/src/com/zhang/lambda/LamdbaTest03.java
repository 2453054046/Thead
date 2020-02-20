package com.zhang.lambda;

public class LamdbaTest03 {
    public static void main(String[] args) {
        //正常使用
        IInterest iInterest = new Interest();
        iInterest.lamdba(10,10);

        //临时扩展或修改Ilove类，简化1
        iInterest = (a,b)-> {
            System.out.println("临时扩展Ilove类  参数是："+(a+b));//加上括号代表运算
            return a+b;
        };
        iInterest.lamdba(11,11);

        //重写简化2
        iInterest = (a,b)->{
            return a+b;
        };
        System.out.println(iInterest.lamdba(12,12));

        //重写简化3   可以不写return
        iInterest = (a,b)-> a+b;
        System.out.println(iInterest.lamdba(13,13));

    }
}

interface IInterest{
    Integer lamdba(int a,int b);
}

class Interest implements IInterest{
    @Override
    public Integer lamdba(int a,int b) {
        System.out.println("参数是："+a+b);
        return a+b;
    }
}