package com.example.nativation.threadtest;

/**
 * 作者：Create on 2020/4/4 22:19  by  wzl
 * 描述：
 * 最近修改：2020/4/4 22:19 modify by wzl
 */
public class Persion extends Thread {
    private int num=50;
    public Persion(String name){
        super(name);
    }
    @Override
    public void run() {
        for (int i=0;i<20;i++){
            if(num>0){
                System.out.println(super.getName()+"吃了编号为"+num-- +"的苹果");
            }
        }
    }
}