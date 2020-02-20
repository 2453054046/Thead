package com.zhang;

import java.util.ArrayList;

public class test {

    public static void main(String[] args) {
        String[] list = new String[]{"我","兔子","桥","钥匙"};
        for(int i=0;i<list.length;i++){
            for(int y=0;y<list.length;y++){
                String j = list[y];
                if(y+1 == list.length){
                    break;
                }
                list[y] = list[y+1];
                list[y+1] = j;
                System.out.println(list[0]+" "+list[1]+" "+list[2]+" "+list[3]);
            }
        }
    }

}
