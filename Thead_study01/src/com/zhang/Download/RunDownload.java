package com.zhang.Download;

/**
 * 开始下载
 */
public class RunDownload implements Runnable {

    private String url; //远程路径
    private String name;  //存储名字

    RunDownload(String url, String name){
        this.url=url;
        this.name=name;

    }

    @Override
    public void run() {
        WebDownload webDownload = new WebDownload();
        webDownload.download(url,name);
    }

    public static void main(String[] args) {
        RunDownload tu1 = new RunDownload("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862542177,1840442879&fm=26&gp=0.jpg", "tu1.jpg");
        RunDownload tu2 = new RunDownload("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862542177,1840442879&fm=26&gp=0.jpg", "tu2.jpg");
        RunDownload tu3 = new RunDownload("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862542177,1840442879&fm=26&gp=0.jpg", "tu3.jpg");
        new Thread(tu1).start();
        new Thread(tu2).start();
        new Thread(tu3).start();

    }
}
