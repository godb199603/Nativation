package com.example.nativation.photo;

import java.util.IllegalFormatCodePointException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 作者：Create on 2020/3/26 21:09  by  wzl
 * 描述：
 * 最近修改：2020/3/26 21:09 modify by wzl
 */
public class RequestManager {


    private static RequestManager requestManager=new RequestManager();
    //创建队列
    private LinkedBlockingQueue<BitmapRequest> requestLinkedBlockingQueue=new LinkedBlockingQueue<>();
   //创建线程数组
    private  BitmapDispatcher[] bitmapDispatchers;

    private RequestManager(){
        start();
    };

    //开启线程开关
    public void start(){
        stopAll();
        startAllDispatcher();
    }

    public static RequestManager getInstance(){
        return  requestManager;
    }

   //将图片请求对象加入队列中
    public void addBitmapRequest(BitmapRequest bitmapRequest){
        if (bitmapRequest == null){
            return;
        }
        //判断请求是否在队列中
        if (!requestLinkedBlockingQueue.contains(bitmapRequest)){
            requestLinkedBlockingQueue.add(bitmapRequest);
        }
    }

    //创建并开始所有线程
    public void startAllDispatcher(){
        //获取手机上单个应用最大线程数
        int threadCount=Runtime.getRuntime().availableProcessors();
        //创建队列
        bitmapDispatchers=new BitmapDispatcher[threadCount];
        for (int i=0;i<threadCount;i++){
            //所有的BitmapDispatcher都持有一个queque
            BitmapDispatcher bitmapDispatcher=new BitmapDispatcher(requestLinkedBlockingQueue);
            bitmapDispatcher.start();
            //将每个bitmapDispatcher放入数组中统一管理
            bitmapDispatchers[i]=bitmapDispatcher;
        }
    }

     //停止所有线程
    public void stopAll(){
        if (bitmapDispatchers!=null&&bitmapDispatchers.length>0){
            for (BitmapDispatcher bitmapDispatcher:bitmapDispatchers){
                if (!bitmapDispatcher.isInterrupted()){
                    //停止线程
                    bitmapDispatcher.interrupt();
                }
            }
        }

    }


}
