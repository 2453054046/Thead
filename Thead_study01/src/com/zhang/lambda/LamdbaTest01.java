package com.zhang.lambda;

public class LamdbaTest01 {
    public static void main(String[] args) {
        //正常使用
        Ilike ilike = new IlikeImpl();
        ilike.lamdba();

        //临时扩展或修改Ilike类
        ilike = ()->{
            System.out.println("临时扩展Ilike类");
        };
        ilike.lamdba();
    }
}
interface Ilike{
    void lamdba();
}
class IlikeImpl implements Ilike{
    @Override
    public void lamdba() {
        System.out.println("外部实现");
    }
}