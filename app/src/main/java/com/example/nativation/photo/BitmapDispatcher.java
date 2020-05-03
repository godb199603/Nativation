package com.example.nativation.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import java.util.IllegalFormatCodePointException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 作者：Create on 2020/3/26 20:00  by  wzl
 * 描述：
 * 最近修改：2020/3/26 20:00 modify by wzl
 */
public class BitmapDispatcher extends Thread{

    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    //创建一个阻塞队列
    private LinkedBlockingQueue<BitmapRequest> requestQueue;
    //切换线程
    private Handler handler=new Handler(Looper.getMainLooper()) ;


    @Override
    public void run(){
        //有没有被干掉
        while(!isInterrupted()){
            try {
                BitmapRequest bitmapRequest=requestQueue.take();
                //先设置占位图片
                showLoadImg(bitmapRequest);
                //加载图片得到图片
                Bitmap bitmap=findBitmap(bitmapRequest);
                //把图片显示到ImageView上
                showImageView(bitmapRequest,bitmap);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void showImageView(final BitmapRequest bitmapRequest, final Bitmap bitmap) {
        //保证imageview要加载的资源一致，不错位
        if (bitmap!=null&& bitmapRequest.getImageView()!=null
                && bitmapRequest.getUrlMd5().equals(bitmapRequest.getImageView().getTag())){
          final ImageView imageView=bitmapRequest.getImageView();
          handler.post(new Runnable() {
              @Override
              public void run() {
                  imageView.setImageBitmap(bitmap);
                  if (bitmapRequest.getRequestListener()!=null){
                      //调用回调接口
                      RequestListener requestListener=bitmapRequest.getRequestListener();
                      requestListener.onSuccess(bitmap);
                  }
              }
          });
    }

}

    private Bitmap findBitmap(BitmapRequest bitmapRequest) {
        Bitmap bitmap=null;
        bitmap=downLoadImage(bitmapRequest.getUrl());
        return bitmap;

    }

    private Bitmap downLoadImage(String uri) {
//        FileOutputStream fos=null;
        InputStream is=null;
        Bitmap bitmap =null;
        try {
            URL url = new URL(uri);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           try{
               if (is!=null)
                   is.close();
               }catch(IOException e){
               e.printStackTrace();
            }
           }
        return bitmap;
    }


    private void showLoadImg(BitmapRequest bitmapRequest) {
        if (bitmapRequest.getResId()>0&&bitmapRequest.getImageView()!=null){
           final int ResId= bitmapRequest.getResId();
           final ImageView imageView=bitmapRequest.getImageView();
           handler.post(new Runnable() {
               @Override
               public void run() {
                   imageView.setImageResource(ResId);
               }
           });
//           imageView.setImageResource(ResId);
        }

    }



}
