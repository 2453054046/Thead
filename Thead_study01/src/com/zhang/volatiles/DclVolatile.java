package com.zhang.volatiles;

/**
 * 单例设计模式：
 * 在内部可以创造多个实例，但对外只有一个实例
 * 1、单例类只能有一个实例。
 * 2、单例类必须自己创建自己的唯一实例。
 * 3、单例类必须给所有其他对象提供这一实例。
 * 使用：
 * 1、构造器私有化 -->避免外部new构造器
 * 2、提供私有的静态属性 -->存储对象的地址
 * 3、提供公共的静态方法 --> 获取属性
 */
public class DclVolatile {
    //volatile防止出现指令重排，实时向主存同步该变量的信息
    private volatile static DclVolatile dclVolatile;
    private DclVolatile(){

    }
    //对外暴露
    public static DclVolatile get(){
        //性能提高
        if(null != dclVolatile){
            return dclVolatile;
        }
        //防止高并发添加线程锁
        synchronized (DclVolatile.class){  //获得该类型的类型类，通过反射获得DclVolatile类
            if(null == dclVolatile){
                dclVolatile = new DclVolatile();
                /**
                 * 创建对象实现细节：
                 * 1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
                 */
            }
        }
        return dclVolatile;
    }
    //对外暴露 测试不加锁的模拟高并发网络延迟
    public static DclVolatile get1(){

            if(null == dclVolatile){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dclVolatile = new DclVolatile();
                /**
                 * 创建对象实现细节：
                 * 1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
                 */
            }

        return dclVolatile;
    }

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(DclVolatile.get());
        }).start();

        System.out.println(DclVolatile.get());
    }
}
