package com.zhang.线程list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写入时复制（CopyOnWrite，简称COW）思想是计算机程序设计领域中的一种优化策略。
 * 其核心思想是，如果有多个调用者（Callers）同时要求相同的资源（如内存或者是磁盘上的数据存储），
 * 他们会共同获取相同的指针指向相同的资源，直到某个调用者视图修改资源内容时，
 * 系统才会真正复制一份专用副本（private copy）给该调用者，而其他调用者所见到的最初的资源仍然保持不变。
 * 这过程对其他的调用者都是透明的（transparently）。
 * 此做法主要的优点是如果调用者没有修改资源，就不会有副本（private copy）被创建，
 * 因此多个调用者只是读取操作时可以共享同一份资源。
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        for (int i = 0; i < 5 ; i++) {
            new Thread(myThread1).start();
        }
    }
}

class MyThread1 implements Runnable{
    /**
     * 注意：添加操作多时，效率低，因为每次添加操作都会进行复制，开销非常大。并发迭代操作多时可以选择
     */
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AAA");
        list.add("BBB");
    }
    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(Thread.currentThread().getName()+"\t"+iterator.next());

            list.add("AA");
        }
    }
}
