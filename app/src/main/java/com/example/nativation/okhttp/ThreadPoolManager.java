package com.example.nativation.okhttp;





import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：Create on 2020/3/24 19:11  by  wzl
 * 描述：
 * 最近修改：2020/3/24 19:11 modify by wzl
 */
public class ThreadPoolManager {

    private static ThreadPoolManager threadPoolManager;
    public static ThreadPoolManager getInstance(){
        return threadPoolManager;
    }
    // 1 创建队列，用来保存异步请求任务
    private LinkedBlockingQueue<Runnable> mQueue=new LinkedBlockingQueue<>();
    public void addTask(Runnable runnable){
        if (runnable!=null)
        try {
            mQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    //创建线程池

    public ThreadPoolExecutor threadPoolExecutor;
    private  ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        //将拒绝的线程重新排队
                        addTask(runnable);
                    }
                }
        );
        //叫号员也应该在线程池运行
        threadPoolExecutor.execute(coreThread);
    }


     //创建叫号员，不停获取
       public  Runnable coreThread=new Runnable() {
              Runnable runn=null;
            @Override
            public void run() {
                while(true){
                    try {
                        runn=mQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //交给线程池处理
                    threadPoolExecutor.execute(runn);
                }
            }
        };






    }

