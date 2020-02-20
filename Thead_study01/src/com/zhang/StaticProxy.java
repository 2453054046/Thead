package com.zhang;

/**
 * 静态代理
 * 公共接口
 * 1、真实角色
 * 2、代理角色
 */
public class StaticProxy {
    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
        /**同理
         * 实现Runnable接口，启动使用Thread静态代理对象
         *  new Thread(子线程类).start();
         */
    }
}

/**
 * 结婚
 */
interface Marry{
    void happyMarry();
}

class You implements Marry{
    //本人结婚
    @Override
    public void happyMarry() {
        System.out.println("真实角色结婚，手动办理婚礼");
    }
}
/**
 * 婚庆代理
 */
class WeddingCompany implements Marry{
    private Marry you;
    WeddingCompany (Marry you) {
        this.you = you;
    }
    @Override
    public void happyMarry() {
        ready();
        this.you.happyMarry();
        after();
    }
    private void ready(){
        System.out.println("布置婚礼");
    }
    private void after(){
        System.out.println("婚礼结束");
    }
}
