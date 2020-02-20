package com.zhang.Download;

/**
 * 开始下载
 */
public class ThreadDownload extends Thread {

    private String url; //远程路径
    private String name;  //存储名字

    ThreadDownload(String url,String name){
        this.url=url;
        this.name=name;

    }

    @Override
    public void run() {
        WebDownload webDownload = new WebDownload();
        webDownload.download(url,name);
    }

    public static void main(String[] args) {
        ThreadDownload tu1 = new ThreadDownload("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862542177,1840442879&fm=26&gp=0.jpg", "tu1.jpg");
        ThreadDownload tu2 = new ThreadDownload("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862542177,1840442879&fm=26&gp=0.jpg", "tu2.jpg");
        ThreadDownload tu3 = new ThreadDownload("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862542177,1840442879&fm=26&gp=0.jpg", "tu3.jpg");
        tu1.start();
        tu2.start();
        tu3.start();

    }
}
