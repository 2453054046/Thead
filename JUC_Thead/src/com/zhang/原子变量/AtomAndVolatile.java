package com.zhang.原子变量;


import java.util.concurrent.atomic.AtomicInteger;

/** 原子变量
 * jdk1.5后java.util.concurrent.atomic包下提供了常用的原子变量
 */
public class AtomAndVolatile {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        //i++操作
        atomicInteger.getAndIncrement();
    }
}
