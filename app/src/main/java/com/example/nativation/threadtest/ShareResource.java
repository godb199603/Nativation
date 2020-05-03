package com.example.nativation.threadtest;

/**
 * 作者：Create on 2020/4/5 22:29  by  wzl
 * 描述：
 * 最近修改：2020/4/5 22:29 modify by wzl
 */
public class ShareResource {
    private String name;
    private String gender;
    //共享资源是否为空
    private boolean isEmpty=true;
    //生产者向共享资源中存储数据
    public void push(String name,String gender){
        try {
            while (!isEmpty) {
                //等待消费者消费，只能被其他线程唤起
                this.wait();
            }
            //生产过程
            this.name = name;
            Thread.sleep(10);
            this.gender=gender;
            //生产结束
            isEmpty=false;
            //唤醒一个消费者
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //消费者从共享内存中取出数据
    public void popop(){
        try{
            while(isEmpty){
                //等待生产者生产
                this.wait();
            }
            Thread.sleep(10);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        //消费开始
        System.out.println(this.name+"-"+this.gender);
        //消费结束
        isEmpty=true;
        //唤醒一个生产者
        this.notify();
    }
}

